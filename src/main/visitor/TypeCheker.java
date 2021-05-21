package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.statement.*;
import main.ast.types.NoType;
import main.ast.types.Type;
import main.ast.types.VoidType;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileErrors.typeErrors.*;
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

    private FunctionSymbolTableItem findFuncSymbolTableItem(FptrType fptr) {
        try{
            String searchKey = FunctionSymbolTableItem.START_KEY + fptr.getFunctionName();
            return (FunctionSymbolTableItem) SymbolTable.root.getItem(searchKey);
        }catch (ItemNotFoundException e){
            return null;
        }
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
        if (lType instanceof ListType && rType instanceof ListType){
            return assignmentHasError(((ListType) lType).getType(), ((ListType) rType).getType());
        }

        if(lType instanceof FptrType){
            if(!(rType instanceof VoidType || rType instanceof FptrType))
                return true;
            if(rType instanceof FptrType){
                FunctionSymbolTableItem lfunc = findFuncSymbolTableItem((FptrType) lType);
                FunctionSymbolTableItem rfunc = findFuncSymbolTableItem((FptrType) rType);
                ArrayList<Type> leftArgsTypes = new ArrayList<>(lfunc.getArgTypes().values());
                ArrayList<Type> rightArgsTypes = new ArrayList<>(rfunc.getArgTypes().values());

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
        curFunction = findFuncSymbolTableItem(new FptrType(funcDeclaration.getFunctionName().getName()));
        expressionTypeChecker.setCurFunction(curFunction);
        funcDeclaration.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(MainDeclaration mainDeclaration) {
        expressionTypeChecker.setMain(true);
        mainDeclaration.getBody().accept(this);
        expressionTypeChecker.setMain(false);
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
        expressionTypeChecker.setFunctionCallStmt(true);
        funcCallStmt.getFunctionCall().accept(expressionTypeChecker);
        expressionTypeChecker.setFunctionCallStmt(false);
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
            ReturnValueNotMatchFunctionReturnType exception = new ReturnValueNotMatchFunctionReturnType(returnStmt.getLine());
            returnStmt.addError(exception);
        }
        return null;
    }
}
