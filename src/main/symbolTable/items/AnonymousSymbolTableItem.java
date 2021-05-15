package main.symbolTable.items;

import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.expression.AnonymousFunction;
import main.ast.types.Type;
import main.symbolTable.SymbolTable;

import java.util.ArrayList;

public class AnonymousSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "Anonymous_";
    private String name;
    private SymbolTable AnonymousSymbolTable;
    private AnonymousFunction anonymousFunction;
    private ArrayList<Type> argTypes;
    private Type returnType;

    public AnonymousSymbolTableItem(AnonymousFunction anonymousFunction, String name) {
        this.anonymousFunction = anonymousFunction;
        this.name = name;
        argTypes = new ArrayList<>();
    }

    public SymbolTable getAnonymousSymbolTable() {
        return AnonymousSymbolTable;
    }

    public void setAnonymousSymbolTable(SymbolTable anonymousSymbolTable) {
        AnonymousSymbolTable = anonymousSymbolTable;
    }

    public AnonymousFunction getAnonymousFunction() {
        return anonymousFunction;
    }

    public void setAnonymousFunction(AnonymousFunction anonymousFunction) {
        this.anonymousFunction = anonymousFunction;
    }

    public void addArgType(Type type) {
        argTypes.add(type);
    }

    public ArrayList<Type> getArgTypes() {
        return argTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKey() {
        return START_KEY + this.name;
    }
}
