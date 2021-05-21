package main.visitor;

import main.ast.nodes.*;
import main.ast.nodes.declaration.*;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.*;
import main.ast.nodes.expression.values.primitive.*;
import main.ast.nodes.statement.*;
import main.ast.types.*;
import main.symbolTable.*;
import main.symbolTable.exceptions.*;
import main.symbolTable.items.*;
import java.util.*;

public class TypeSetter  extends Visitor<Void> {
    private boolean fCall = false;
    private TypeInference typeInference;
    private Set<String> visited;
    private ArrayList<ArrayList<String>> visitOrder;
    private ArrayList<String> temp;
    private FunctionSymbolTableItem curFuncSymbolTableItem;
    private boolean add = true;
    public TypeSetter() {
        typeInference = new TypeInference(this);
        visited = new HashSet<>();
        visitOrder = new ArrayList<>();
        temp = new ArrayList<>();
    }

    public Set<String> getVisited() {
        return visited;
    }

    private SymbolTable findFuncSymbolTable(Identifier name) {
        try {
            FunctionSymbolTableItem fItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + name.getName());
            curFuncSymbolTableItem = fItem;
            return fItem.getFunctionSymbolTable();
        }catch (ItemNotFoundException e) {
            return null;
        }

    }

    @Override
    public Void visit(Program program) {
        program.getMain().accept(this);
        visitOrder.add(0,temp);
        add = false;
        for (int j=visitOrder.size() -1; j>=0 ; j--) {
            for (int i = visitOrder.get(j).size()-1; i >= 0; i--) {
                try {
                    FunctionSymbolTableItem fItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + visitOrder.get(j).get(i));
                    if (fItem.getReturnType() == null) {
                        visited.remove(visitOrder.get(j).get(i));
                        fItem.getFuncDeclaration().accept(this);
                    }
                    if (fItem.getReturnType() == null)
                        fItem.setReturnType(new NoType());
                } catch (ItemNotFoundException e) {

                }
            }
        }
        for (FunctionDeclaration funcDec : program.getFunctions()) {
            if (! visited.contains(funcDec.getFunctionName().getName()))
                continue;
            try {
                FunctionSymbolTableItem fItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + funcDec.getFunctionName().getName());
                FunctionDeclaration fDec = fItem.getFuncDeclaration();
                int i = 0;
                System.out.println(fDec.getFunctionName().getName());
                System.out.println(fItem.getArgTypes().values());
                System.out.println(fItem.getReturnType());
            }catch (ItemNotFoundException e) {

            }

        }
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration funcDeclaration) {
        if(visited.contains(funcDeclaration.getFunctionName().getName())) {
            if (add) {
                visitOrder.add(0, (ArrayList<String>) temp.clone());
                temp.clear();
            }
            return null;
        }
        visited.add(funcDeclaration.getFunctionName().getName());
        if(add)
            temp.add(funcDeclaration.getFunctionName().getName());
        typeInference.addVisited(funcDeclaration.getFunctionName().getName());
        FunctionSymbolTableItem temp = curFuncSymbolTableItem;
        typeInference.setFunctionSymbolTable(findFuncSymbolTable(funcDeclaration.getFunctionName()));
        funcDeclaration.getBody().accept(this);
        curFuncSymbolTableItem = temp;
        if (curFuncSymbolTableItem != null)
            typeInference.setFunctionSymbolTable(curFuncSymbolTableItem.getFunctionSymbolTable());
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
        conditionalStmt.getCondition().accept(this);
        conditionalStmt.getThenBody().accept(this);

        if(conditionalStmt.getElseBody() != null) {
            conditionalStmt.getElseBody().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FunctionCallStmt funcCallStmt) {
        funcCallStmt.getFunctionCall().accept(typeInference);
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        print.getArg().accept(this);
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        if(curFuncSymbolTableItem.getReturnType() == null || curFuncSymbolTableItem.getReturnType() instanceof NoType) {

            Type returnType = returnStmt.getReturnedExpr().accept(typeInference);
            curFuncSymbolTableItem.setReturnType(returnType);
        }
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        binaryExpression.getFirstOperand().accept(this);
        binaryExpression.getSecondOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        unaryExpression.getOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(AnonymousFunction anonymousFunction) {
        for (Identifier arg: anonymousFunction.getArgs())
            arg.accept(this);
        anonymousFunction.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        return null;
    }

    @Override
    public Void visit(ListAccessByIndex listAccessByIndex) {
        listAccessByIndex.getInstance().accept(this);
        listAccessByIndex.getIndex().accept(this);
        return null;
    }

    @Override
    public Void visit(ListSize listSize) {
        listSize.getInstance().accept(this);
        return null;
    }



    @Override
    public Void visit(ListValue listValue) {
        for (Expression element : listValue.getElements())
            element.accept(this);
        return null;
    }

    @Override
    public Void visit(IntValue intValue) {
        return null;
    }

    @Override
    public Void visit(BoolValue boolValue) {
        return null;
    }

    @Override
    public Void visit(StringValue stringValue) {
        return null;
    }

    @Override
    public Void visit(VoidValue voidValue) {
        return null;
    }


}
