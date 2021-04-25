package main.visitor.typeVisitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.expression.AnonymousFunction;
import main.ast.nodes.expression.BinaryExpression;
import main.ast.nodes.expression.Identifier;
import main.ast.nodes.expression.UnaryExpression;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.statement.*;
import main.compileError.typeError.ConditionNotBool;
import main.compileError.typeError.OperatorsNotMatch;
import main.visitor.Visitor;

import java.util.HashSet;
import java.util.Set;

public class TypeMatch extends Visitor<Void> {
    private Set<BinaryOperator> operators = new HashSet<>();
    private boolean unaryExpressionVisit = false;
    private boolean isBoolOp() {
        return operators.contains(BinaryOperator.and) || operators.contains(BinaryOperator.or);
    }

    private boolean isIntOP() {
        return operators.contains(BinaryOperator.gt) || operators.contains(BinaryOperator.lt)
                || operators.contains(BinaryOperator.add) || operators.contains(BinaryOperator.sub)
                || operators.contains(BinaryOperator.mult) || operators.contains(BinaryOperator.div)
                || unaryExpressionVisit;
    }
    private boolean operatorsMatch() {
       boolean boolOp = isBoolOp();
       boolean intOp = isIntOP();
       if ((boolOp && !intOp) || (intOp && !boolOp) || operators.size() == 0)
           return true;
       else
           return false;

    }
    @Override
    public Void visit(Program program) {
        for (FunctionDeclaration funcDec: program.getFunctions())
            funcDec.accept(this);
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
        operators.clear();
        unaryExpressionVisit = false;

        conditionalStmt.getCondition().accept(this);
        if (!operatorsMatch()) {
            OperatorsNotMatch exception = new OperatorsNotMatch(conditionalStmt.getCondition().getLine());
            conditionalStmt.addError(exception);
        }
        else {
            if(!isBoolOp()) {
                ConditionNotBool exception = new ConditionNotBool(conditionalStmt.getCondition().getLine());
                conditionalStmt.addError(exception);
            }
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
        operators.clear();
        unaryExpressionVisit = false;
        print.getArg().accept(this);
        if (!operatorsMatch()) {
            OperatorsNotMatch exception = new OperatorsNotMatch(print.getArg().getLine());
            print.addError(exception);
        }
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        operators.clear();
        unaryExpressionVisit = false;
        returnStmt.getReturnedExpr().accept(this);
        if (!operatorsMatch()) {
            OperatorsNotMatch exception = new OperatorsNotMatch(returnStmt.getReturnedExpr().getLine());
            returnStmt.addError(exception);
        }
        return null;
    }

    @Override
    public Void visit(AnonymousFunction anonymousFunction) {
        anonymousFunction.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        operators.add(binaryExpression.getBinaryOperator());
        binaryExpression.getFirstOperand().accept(this);
        binaryExpression.getSecondOperand().accept(this);
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        unaryExpressionVisit = true;
        unaryExpression.getOperand().accept(this);
        return null;
    }





}




