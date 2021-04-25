package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.declaration.MainDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.statement.*;
import main.compileError.CompileError;
import main.compileError.nameError.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.symbolTable.items.SymbolTableItem;
import main.symbolTable.items.VariableSymbolTableItem;

import java.util.ArrayList;
import java.util.Map;

public class NameAnalyser extends Visitor<Void> {
    private int error;
    private ArrayList<CompileError> nameErrors;
    private boolean fcall = false;
    private boolean funcDeclared;
    private FunctionDeclaration fdec;

    public NameAnalyser(){
        nameErrors = new ArrayList<>();
    }

    public ArrayList<CompileError> getError() {return this.nameErrors;}



    @Override
    public Void visit(Program program) {
        SymbolTable root = new SymbolTable();
        SymbolTable.root = root;
        SymbolTable.push(root);

        for (FunctionDeclaration funcDec: program.getFunctions()) {
            SymbolTable newSymbolTable = new SymbolTable();
            FunctionSymbolTableItem newSymbolTableItem = new FunctionSymbolTableItem(funcDec);
            newSymbolTableItem.setFunctionSymbolTable(newSymbolTable);
            try {
                root.put(newSymbolTableItem);

            } catch (ItemAlreadyExistsException e) {
                this.error += 1;
                nameErrors.add(new DuplicateFunction(funcDec.getLine(), funcDec.getFunctionName().getName()));
                String newName = funcDec.getFunctionName().getName() + this.error + "@";
                funcDec.setFunctionName(new Identifier(newName));
                try {
                    FunctionSymbolTableItem newFuncSym = new FunctionSymbolTableItem(funcDec);
                    newFuncSym.setFunctionSymbolTable(newSymbolTable);
                    root.put(newFuncSym);
                } catch (ItemAlreadyExistsException e1) { //Unreachable
                }
            }
            SymbolTable.push(newSymbolTable);
        }

        program.getMain().accept(this);
        for (FunctionDeclaration funcDec: program.getFunctions()){
            SymbolTableItem curSymbolTableItem;
            FunctionSymbolTableItem functionSymbolTableItem;
            try{
                curSymbolTableItem = SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + funcDec.getFunctionName().getName());
                functionSymbolTableItem = (FunctionSymbolTableItem) curSymbolTableItem;
                SymbolTable.push(functionSymbolTableItem.getFunctionSymbolTable());
                funcDec.accept(this);
                SymbolTable.pop();
            }catch (ItemNotFoundException e){ //Unreachable
            }
        }


        return null;
    }

    @Override
    public Void visit (MainDeclaration mainDeclaration) {
        mainDeclaration.getBody().accept(this);
        return null;

    }

    @Override
    public Void visit (FunctionDeclaration funcDec) {
        for (Identifier arg: funcDec.getArgs()) {
            VariableSymbolTableItem varSym = new VariableSymbolTableItem(arg);
            try {
                SymbolTable.top.put(varSym);

            } catch (ItemAlreadyExistsException e) {
                error += 1;
                nameErrors.add(new DuplicateArgument(arg.getLine(), arg.getName()));
            }
            try{
                SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + arg.getName());
                error += 1;
                nameErrors.add(new NameConflict(arg.getLine(), arg.getName()));
            }catch (ItemNotFoundException e) {
                //unreachable
            }
        }
        funcDec.getBody().accept(this);
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
        SymbolTable tempSymbolTable = new SymbolTable();
        SymbolTable.push(tempSymbolTable);

        for (Identifier arg: anonymousFunction.getArgs()) {
            VariableSymbolTableItem varSym = new VariableSymbolTableItem(arg);
            try {
                SymbolTable.top.put(varSym);

            } catch (ItemAlreadyExistsException e) {
                error += 1;
                nameErrors.add(new DuplicateArgument(arg.getLine(), arg.getName()));
                /*System.out.println("Line:" + arg.getLine() + ":" + "Duplicate argument " + arg.getName());*/
            }
            try{
                SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + arg.getName());
                error += 1;
                nameErrors.add(new NameConflict(arg.getLine(), arg.getName()));
                /*System.out.println("Line:" + arg.getLine() + ":" + "Name of argument " + arg.getName() +
                        " conflicts with a function’s name");*/
            }catch (ItemNotFoundException e) {
                //unreachable
            }
        }
        anonymousFunction.getBody().accept(this);
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        if (fcall) {
            try{
                FunctionSymbolTableItem fitem = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + identifier.getName());
                fdec = fitem.getFuncDeclaration();
                funcDeclared = true;

            }catch (ItemNotFoundException e) {
                nameErrors.add(new FunctionNotDeclared(identifier.getLine(), identifier.getName()));
                /*System.out.println("Line:" + identifier.getLine() + "Function " + identifier.getName()  +
                        " not declared");*/
                funcDeclared = false;
                error += 1;
            }
            return null;
        }
        try{
            SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + identifier.getName());

        }catch (ItemNotFoundException e) {
            try{
                SymbolTable.top.getItem(VariableSymbolTableItem.START_KEY + identifier.getName());

            } catch (ItemNotFoundException e1) {
                nameErrors.add(new VariableNotDeclared(identifier.getLine(), identifier.getName()));
                /*System.out.println("Line:" + identifier.getLine() + ":" + "Variable " + identifier.getName() +
                        " not declared");*/
                error += 1;
            }
        }


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
    public Void visit(FunctionCall funcCall) {
        fcall = true;
        funcCall.getInstance().accept(this);
        fcall = false;
        for (Expression args: funcCall.getArgs())
            args.accept(this);
        for (Map.Entry<Identifier,Expression> argsWithKey: funcCall.getArgsWithKey().entrySet()){
            //argsWithKey.getKey().accept(this);
            argsWithKey.getValue().accept(this);
            boolean match = false;
            if (funcDeclared) {
                for (Identifier id : fdec.getArgs()) {
                    if (id.getName().equals(argsWithKey.getKey().getName())) {
                        match = true;
                        break;
                    }
                }
                if(!match) {
                    nameErrors.add(new ArgumentNotDeclared( argsWithKey.getKey().getLine(),
                                                            argsWithKey.getKey().getName(),
                                                            fdec.getFunctionName().getName()));
                    error += 1;
                }
            }
        }
        return null;
    }
}
