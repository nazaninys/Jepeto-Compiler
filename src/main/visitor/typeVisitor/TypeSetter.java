package main.visitor.typeVisitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.expression.BinaryExpression;
import main.ast.nodes.expression.Identifier;
import main.ast.nodes.expression.UnaryExpression;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.operators.UnaryOperator;
import main.ast.nodes.statement.*;
import main.visitor.Visitor;

public class TypeSetter extends Visitor<Void> {
    private FunctionDeclaration curFunc;
    private boolean opExpr = false;
    private boolean intOp = false;
    private boolean boolOp = false;

    private boolean isBoolOp(BinaryOperator op) {
        return op.equals(BinaryOperator.and) || op.equals(BinaryOperator.or);
    }

    private boolean isIntOP(BinaryOperator op) {
        return op.equals(BinaryOperator.gt) || op.equals(BinaryOperator.lt)
                || op.equals(BinaryOperator.add) || op.equals(BinaryOperator.sub)
                || op.equals(BinaryOperator.mult) || op.equals(BinaryOperator.div);
    }

    private boolean isUnaryIntOP (UnaryOperator op) {
        return op.equals(UnaryOperator.not) || op.equals(UnaryOperator.minus);
    }

    @Override
    public Void visit(Program program) {
        for (FunctionDeclaration funcDec: program.getFunctions()) {
            curFunc = funcDec;
            funcDec.accept(this);
        }
        program.getMain().accept(this);
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration funcDeclaration) {
        for (Identifier arg: funcDeclaration.getArgs())
            arg.accept(this);
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
        conditionalStmt.getCondition().accept(this);
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
        print.getArg().accept(this);
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        returnStmt.getReturnedExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        boolOp = isBoolOp(binaryExpression.getBinaryOperator());
        intOp = isIntOP(binaryExpression.getBinaryOperator());
        opExpr = true;
        binaryExpression.getFirstOperand().accept(this);
        binaryExpression.getSecondOperand().accept(this);
        opExpr = false;
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        opExpr = true;
        intOp = isUnaryIntOP(unaryExpression.getOperator());
        unaryExpression.getOperand().accept(this);
        opExpr = false;
        return null;
    }



}
