package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.ListValue;
import main.ast.nodes.expression.values.VoidValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.nodes.statement.*;

import java.util.*;

public class ASTTreePrinter extends Visitor<Void> {

    @Override
    public Void visit(Program program) {
        System.out.println("Line:" + program.getLine() + ":" + program.toString());
        for (FunctionDeclaration funcDec: program.getFunctions())
            funcDec.accept(this);

        program.getMain().accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration funcDeclaration) {
        System.out.println("Line:" + funcDeclaration.getLine() + ":" + funcDeclaration.toString());
        for (Identifier arg: funcDeclaration.getArgs())
            arg.accept(this);
        funcDeclaration.getBody().accept(this);
        return null;

    }

    @Override
    public Void visit(MainDeclaration mainDeclaration) {
        System.out.println("Line:" + mainDeclaration.getLine() + ":" + mainDeclaration.toString());
        mainDeclaration.getBody().accept(this);
        return null;

    }


    @Override
    public Void visit(BlockStmt blockStmt) {
        System.out.println("Line:" + blockStmt.getLine() + ":" +blockStmt.toString());
        for (Statement stmt: blockStmt.getStatements())
            stmt.accept(this);

        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        System.out.println("Line:" + conditionalStmt.getLine() + ":" + conditionalStmt.toString());
        conditionalStmt.getCondition().accept(this);
        conditionalStmt.getThenBody().accept(this);

        if(conditionalStmt.getElseBody() != null) {
            conditionalStmt.getElseBody().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FunctionCallStmt funcCallStmt) {
        System.out.println("Line:" + funcCallStmt.getLine() + ":" + funcCallStmt.toString());
        funcCallStmt.getFunctionCall().accept(this);
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        System.out.println("Line:" + print.getLine() + ":" +print.toString());
        print.getArg().accept(this);
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        System.out.println("Line:" + returnStmt.getLine() + ":" +returnStmt.toString());
        returnStmt.getReturnedExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        System.out.println("Line:" + binaryExpression.getLine() + ":" +binaryExpression.toString());

        binaryExpression.getFirstOperand().accept(this);
        binaryExpression.getSecondOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        System.out.println("Line:" + unaryExpression.getLine() + ":" +unaryExpression.toString());
        unaryExpression.getOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(AnonymousFunction anonymousFunction) {
        System.out.println("Line:" + anonymousFunction.getLine() + ":" +anonymousFunction.toString());
        for (Identifier arg: anonymousFunction.getArgs())
            arg.accept(this);
        anonymousFunction.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        System.out.println("Line:" + identifier.getLine() + ":" +identifier.toString());
        return null;
    }

    @Override
    public Void visit(ListAccessByIndex listAccessByIndex) {
        System.out.println("Line:" + listAccessByIndex.getLine() + ":" +listAccessByIndex.toString());
        listAccessByIndex.getInstance().accept(this);
        listAccessByIndex.getIndex().accept(this);
        return null;
    }

    @Override
    public Void visit(ListSize listSize) {
        System.out.println("Line:" +listSize.getLine() + ":" +listSize.toString());
        listSize.getInstance().accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionCall funcCall) {
        System.out.println("Line:" + funcCall.getLine() + ":" + funcCall.toString());
        funcCall.getInstance().accept(this);
        for (Expression args: funcCall.getArgs())
            args.accept(this);
        for (Map.Entry<Identifier,Expression> argsWithKey: funcCall.getArgsWithKey().entrySet()){
            argsWithKey.getKey().accept(this);
            argsWithKey.getValue().accept(this);

        }
        return null;
    }

    @Override
    public Void visit(ListValue listValue) {
        System.out.println("Line:" + listValue.getLine() + ":" + listValue.toString());
        for (Expression element : listValue.getElements())
            element.accept(this);
        return null;
    }

    @Override
    public Void visit(IntValue intValue) {
        System.out.println("Line:" + intValue.getLine() + ":" + intValue.toString());
        return null;
    }

    @Override
    public Void visit(BoolValue boolValue) {
        System.out.println("Line:" + boolValue.getLine() + ":" + boolValue.toString());
        return null;
    }

    @Override
    public Void visit(StringValue stringValue) {
        System.out.println("Line:" + stringValue.getLine() + ":" + stringValue.toString());
        return null;
    }

    @Override
    public Void visit(VoidValue voidValue) {
        System.out.println("Line:" + voidValue.getLine() + ":" + voidValue.toString());
        return null;
    }

}
