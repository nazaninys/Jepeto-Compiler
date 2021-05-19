package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.statement.*;
import main.ast.types.NoType;
import main.ast.types.Type;
import main.ast.types.VoidType;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListNameType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.ClassType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileError.typeErrors.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.FunctionSymbolTableItem;

import java.util.*;

public class TypeCheker extends Visitor<Void> {
    private ExpressionTypeChecker expressionTypeChecker;
    private FunctionSymbolTableItem curFunction;
    private Set<String> visited;

    public TypeCheker(Set<String> visited) {
        expressionTypeChecker = new ExpressionTypeChecker();
        this.visited = visited;
    }



    public boolean assignmentHasError(Type lType, Type rType){
        if (lType instanceof NoType || rType instanceof NoType)
            return false;
        if(lType instanceof VoidType && !(rType instanceof VoidType))
            return true;
        if ((lType instanceof IntType && !(rType instanceof IntType)))
            return true;
        if ((lType instanceof BoolType && !(rType instanceof BoolType)))
            return true;
        if ((lType instanceof StringType && !(rType instanceof StringType)))
            return true;
//        if ((lType instanceof ListType && !(rType instanceof ListType)))
//            return true;

//        if(lType instanceof ListType && rType instanceof ListType){
//            ArrayList<ListNameType> lList = ((ListType) lType).getElementsTypes();
//            ArrayList<ListNameType> rList = ((ListType) rType).getElementsTypes();
//            if(lList.size() != rList.size())
//                return true;
//            else {
//                for(int i = 0; i < lList.size(); i++){
//                    if(assignmentHasError(lList.get(i).getType(),rList.get(i).getType()))
//                        return true;
//                }
//            }
//
//        }

        if(lType instanceof FptrType){
            if(!(rType instanceof VoidType || rType instanceof FptrType))
                return true;
            if(rType instanceof FptrType){
                FunctionSymbolTableItem lfunc = findFunccSymobolTableItem((FptrType) lType);
                FunctionSymbolTableItem rfunc = findFunccSymobolTableItem((FptrType) rType);
                ArrayList<Type> leftArgsTypes = lfunc.getArgTypes();
                ArrayList<Type> rightArgsTypes = rfunc.getArgTypes();

                Type rightRetType = lfunc.getReturnType();
                Type leftRetType = rfunc.getReturnType();
                if(assignmentHasError(leftRetType,rightRetType))
                    return true;
                if(leftArgsTypes.size() != rightArgsTypes.size())
                    return true;
                else
                    for(int i = 0; i < leftArgsTypes.size(); i++)
                        if(assignmentHasError(leftArgsTypes.get(i),rightArgsTypes.get(i)))
                            return true;
            }
        }
        return false;
    }

    private FunctionSymbolTableItem findFunccSymobolTableItem(FptrType fptr) {
        try{
            FunctionSymbolTableItem func = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + fptr.getFunctionName());
            return func;
        }catch (ItemNotFoundException e){
            return null;
        }

    }


    @Override
    public Void visit(Program program) {
        program.getMain().accept(this);
        for (FunctionDeclaration funcDec: program.getFunctions())
            if (visited.contains(funcDec.getFunctionName().getName()))
                funcDec.accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration funcDeclaration) {
        curFunction = findFunccSymobolTableItem(new FptrType(funcDeclaration.getFunctionName().getName()));
        expressionTypeChecker.setCurFunction(curFunction);
        funcDeclaration.getBody().accept(this);
        return null;

    }

    @Override
    public Void visit(MainDeclaration mainDeclaration) {
        mainDeclaration.getBody().accept(this);
        return null;

    }


    @Override
    public Void visit(BlockStmt blockStmt) {
        for (Statement stmt: blockStmt.getStatements())
            stmt.accept(this);

        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        Type condType = conditionalStmt.getCondition().accept(expressionTypeChecker);
        if(!(condType instanceof BoolType || condType instanceof NoType)) {
            ConditionNotBool exception = new ConditionNotBool(conditionalStmt.getLine());
            conditionalStmt.addError(exception);
        }
        conditionalStmt.getThenBody().accept(this);

        if(conditionalStmt.getElseBody() != null) {
            conditionalStmt.getElseBody().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FunctionCallStmt funcCallStmt) {
        funcCallStmt.getFunctionCall().accept(this);
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        Type argType =  print.getArg().accept(expressionTypeChecker);
        if(argType instanceof FptrType) {
            UnsupportedTypeForPrint exception = new UnsupportedTypeForPrint(print.getLine());
            print.addError(exception);
        }
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        Type returnType = returnStmt.getReturnedExpr().accept(expressionTypeChecker);

        if(assignmentHasError(curFunction.getReturnType(), returnType)){
            ReturnValueNotMatchFunctionReturnType exception = new ReturnValueNotMatchFunctionReturnType(returnStmt);
            returnStmt.addError(exception);
        }
        return null;
    }
}
