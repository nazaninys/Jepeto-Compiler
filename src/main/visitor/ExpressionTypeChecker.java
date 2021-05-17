package main.visitor;

import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.operators.UnaryOperator;
import main.ast.nodes.expression.values.ListValue;
import main.ast.nodes.expression.values.VoidValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.types.NoType;
import main.ast.types.Type;
import main.ast.types.VoidType;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.ClassType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileError.nameError.FunctionNotDeclared;
import main.compileError.nameError.VariableNotDeclared;
import main.compileError.typeErrors.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.symbolTable.items.VariableSymbolTableItem;

import java.util.*;

public class ExpressionTypeChecker extends Visitor<Type>{
    private FunctionSymbolTableItem curFunction;

    public void setCurFunction(FunctionSymbolTableItem curFunction) {
        this.curFunction = curFunction;
    }

    private FunctionSymbolTableItem findFunccSymobolTableItem(FptrType fptr) {
        try{
            FunctionSymbolTableItem func = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + fptr.getFunctionName());
            return func;
        }catch (ItemNotFoundException e){
            return null;
        }

    }

    private boolean sameType(Type el1,Type el2){

        if(el1 instanceof NoType || el2 instanceof NoType)
            return true;
        if(el1 instanceof IntType  && el2 instanceof IntType)
            return true;
        if(el1 instanceof BoolType && el2 instanceof BoolType)
            return true;
        if(el1 instanceof StringType && el2 instanceof StringType)
            return true;
        if(el1 instanceof VoidType && el2 instanceof VoidType)
            return true;
        //ToDo check
        if (el1 instanceof ListType && el2 instanceof ListType){
            return sameType(((ListType) el1).getType(), ((ListType) el2).getType());
        }
        if(el1 instanceof FptrType && el2 instanceof FptrType){
            FunctionSymbolTableItem f1 = findFunccSymobolTableItem((FptrType) el1);
            FunctionSymbolTableItem f2 = findFunccSymobolTableItem((FptrType) el2);
            Type el1RetType = f1.getReturnType();
            Type el2RetType = f2.getReturnType();
            if(!sameType(el1RetType,el2RetType))
                return false;
            ArrayList<Type> el1ArgsTypes = f1.getArgTypes();
            ArrayList<Type> el2ArgsTypes = f2.getArgTypes();

            if(el1ArgsTypes.size() != el2ArgsTypes.size())
                return false;
            else{
                for(int i = 0; i < el1ArgsTypes.size(); i++){
                    if(!sameType(el1ArgsTypes.get(i),el2ArgsTypes.get(i)))
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        boolean rvalue = false;
        Expression left = binaryExpression.getFirstOperand();
        Expression right = binaryExpression.getSecondOperand();

        Type tl = left.accept(this);
        Type tr = right.accept(this);
        BinaryOperator operator =  binaryExpression.getBinaryOperator();


        if (operator.equals(BinaryOperator.and) || operator.equals(BinaryOperator.or)){
            if (tl instanceof BoolType && tr instanceof BoolType)
                return new BoolType();

            if ((tl instanceof NoType || tl instanceof BoolType) &&
                    (tr instanceof BoolType || tr instanceof NoType))
                return new NoType();
        }

        if(operator.equals(BinaryOperator.eq) || operator.equals(BinaryOperator.neq)){
            if(tl instanceof ListType || tr instanceof ListType){
                UnsupportedOperandType exception = new UnsupportedOperandType(left.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            if(!sameType(tl,tr)){
                UnsupportedOperandType exception = new UnsupportedOperandType(right.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            else
                return new BoolType();
        }

        if(operator.equals(BinaryOperator.gt) || operator.equals(BinaryOperator.lt)){
            if (tl instanceof IntType && tr instanceof IntType)
                return new BoolType();

            if ((tl instanceof NoType || tl instanceof IntType) &&
                    (tr instanceof IntType || tr instanceof NoType))
                return new NoType();
        }
        //ToDo check beshe
        if (operator.equals(BinaryOperator.append)) {
            if(tl instanceof ListType && sameType(((ListType) tl).getType(), tr))
                return new ListType(tr);
            if(tl instanceof NoType)
                return new NoType();
        }

        else { // + - / * %
            if (tl instanceof IntType && tr instanceof IntType)
                return new IntType();

            if ((tl instanceof NoType || tl instanceof IntType) &&
                    (tr instanceof IntType || tr instanceof NoType))
                return new NoType();
        }

        UnsupportedOperandType exception = new UnsupportedOperandType(left.getLine(), operator.name());
        left.addError(exception);
        return new NoType();
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {
        Expression uExpr = unaryExpression.getOperand();
        Type ut = uExpr.accept(this);
        UnaryOperator operator = unaryExpression.getOperator();

        if(operator.equals(UnaryOperator.not)){
            if(ut instanceof BoolType)
                return new BoolType();
            if(ut instanceof NoType)
                return new NoType();
            else{
                UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
                uExpr.addError(exception);
                return new NoType();
            }
        }

        else { //-
            if (ut instanceof IntType)
                return new IntType();
            if(ut instanceof NoType)
                return new NoType();
            else{
                UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
                uExpr.addError(exception);
                return new NoType();
            }
        }
    }

    @Override
    public Type visit(AnonymousFunction anonymousFunction) {
        //ToDo
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        try{
           VariableSymbolTableItem var = (VariableSymbolTableItem) curFunction.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY + identifier.getName());
           return var.getType();
        } catch (ItemNotFoundException e1) {
            return null;
        }

    }

    @Override
    public Type visit(ListAccessByIndex listAccessByIndex) {
        Expression index = listAccessByIndex.getIndex();
        Type indexType = listAccessByIndex.getIndex().accept(this);
        Type instanceType = listAccessByIndex.getInstance().accept(this);

        if(!(indexType instanceof IntType || indexType instanceof NoType)){
            ListIndexNotInt exception = new ListIndexNotInt(listAccessByIndex.getLine());
            listAccessByIndex.addError(exception);
        }
        if(instanceType instanceof NoType)
            return new NoType();

        if(!(instanceType instanceof ListType)){
            ListAccessByIndexOnNoneList exception = new ListAccessByIndexOnNoneList(listAccessByIndex.getLine());
            listAccessByIndex.addError(exception);
            return new NoType();
        }

        else {
            if (indexType instanceof IntType)
                return ((ListType) instanceType).getType();
            else
                return new NoType();
        }
    }

    @Override
    public Type visit(ListSize listSize) {
        Type instanceType = listSize.getInstance().accept(this);
        if(instanceType instanceof ListType)
            return new IntType();
        else {
            if(!(instanceType instanceof NoType)) {
                GetSizeOfNoneList exception = new GetSizeOfNoneList(listSize.getLine());
                listSize.addError(exception);
            }
            return new NoType();
        }
    }

    @Override
    public Type visit(FunctionCall funcCall) {
        Type type = funcCall.getInstance().accept(this);
        ArrayList<Type> rtypes = new ArrayList<>();
        for (Expression expression : funcCall.getArgs()) {
            Type t = expression.accept(this);
            rtypes.add(t);
        }

        if (!(type instanceof FptrType || type instanceof NoType)){
            CallOnNoneFptrType exception = new CallOnNoneFptrType(funcCall.getLine());
            funcCall.addError(exception);
            return new NoType();
        }
        //ToDo match definition - use value of void function

        return null;


    }

    @Override
    public Type visit(ListValue listValue) {
        if (listValue.getElements().size() == 0)
            return new ListType(new NoType());

        Type type = listValue.getElements().get(0).accept(this);
        for (Expression element : listValue.getElements()) {
            Type elementType = element.accept(this);
            if (elementType != type) {
                ListElementsTypeNotMatch exception = new ListElementsTypeNotMatch(listValue.getLine());
                listValue.addError(exception);
                return new NoType();
            }
        }
        return new ListType(type);
    }

    @Override
    public Type visit(IntValue intValue) {
        return new IntType();
    }

    @Override
    public Type visit(BoolValue boolValue) {
        return new BoolType();
    }

    @Override
    public Type visit(StringValue stringValue) {
        return new StringType();
    }

    @Override
    public Type visit(VoidValue voidValue) {
        return new VoidType();
    }
}
