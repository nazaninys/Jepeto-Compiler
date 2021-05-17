package main.visitor;

import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.expression.AnonymousFunction;
import main.ast.nodes.expression.Expression;
import main.ast.nodes.expression.FunctionCall;
import main.ast.nodes.expression.Identifier;
import main.ast.nodes.expression.values.*;
import main.ast.nodes.expression.values.primitive.*;
import main.ast.types.*;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.single.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.symbolTable.items.VariableSymbolTableItem;
import java.util.*;

public class TypeInference extends Visitor<Type> {
    private SymbolTable functionSymbolTable;
    private final Set<String> visited;
    private final TypeSetter typeSetter;
    private final int numOfAnonymous = 0;

    public TypeInference(TypeSetter typeSetter) {
        this.typeSetter = typeSetter;
        visited = new HashSet<>();
    }

    public void setFunctionSymbolTable(SymbolTable functionSymbolTable) {
        this.functionSymbolTable = functionSymbolTable;
    }

    public void addVisited(String s) {
        visited.add(s);
    }

    private void setArgumentsType(FunctionSymbolTableItem fItem, ArrayList<Type> types) {
        FunctionDeclaration fDec = fItem.getFuncDeclaration();
        if (visited.contains(fDec.getFunctionName().getName()))
            return;
        int min = Math.min(fDec.getArgs().size(), types.size());
        for (int i=0; i < min; i += 1) {
            try {
                VariableSymbolTableItem varSym = (VariableSymbolTableItem) fItem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY +  fDec.getArgs().get(i).getName());
                varSym.setType(types.get(i));
                fItem.addArgType(types.get(i));
            }catch(ItemNotFoundException e) {//unReachable
            }
        }
        if (fDec.getArgs().size() > min) {
            for(int i=min; i < fDec.getArgs().size(); i+= 1) {
                try {
                    VariableSymbolTableItem varSym = (VariableSymbolTableItem) fItem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY + fDec.getArgs().get(i).getName());
                    varSym.setType(new NoType());
                    fItem.addArgType(new NoType());
                }catch (ItemNotFoundException e) {//unReachable
                }
            }
        }
    }

    private void setArgumentsTypeWithKey(FunctionSymbolTableItem fItem, Map<String, Type> types) {
        FunctionDeclaration fDec = fItem.getFuncDeclaration();
        if (visited.contains(fDec.getFunctionName().getName()))
            return;
        for (Identifier arg: fDec.getArgs()) {
            try {
                VariableSymbolTableItem varSym = (VariableSymbolTableItem) fItem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY + arg.getName());
                if(types.containsKey(arg.getName())) {
                    varSym.setType(types.get(arg.getName()));
                    fItem.addArgType(types.get(arg.getName()));
                }
                else {
                    varSym.setType(new NoType());
                    fItem.addArgType(new NoType());
                }
            }catch (ItemNotFoundException e) {//unReachable
            }
        }
    }

    @Override
    public Type visit(FunctionCall funcCall) {
        Type instanceType = funcCall.getInstance().accept(this);
        ArrayList<Type> argsType = new ArrayList<>();
        Map<String, Type> argsWithKeyType = new HashMap<>();
        if (instanceType instanceof FptrType) {
            for (Expression args : funcCall.getArgs())
                argsType.add(args.accept(this));

            for (Map.Entry<Identifier, Expression> argsWithKey : funcCall.getArgsWithKey().entrySet()) {
                Type type = argsWithKey.getValue().accept(this);
                argsWithKeyType.put(argsWithKey.getKey().getName(), type);
            }
            try {
                String fKey = FunctionSymbolTableItem.START_KEY + ((FptrType) instanceType).getFunctionName();
                FunctionSymbolTableItem fItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(fKey);
                if(argsWithKeyType.size() != 0)
                    setArgumentsTypeWithKey(fItem, argsWithKeyType);
                else
                    setArgumentsType(fItem, argsType);

                fItem.getFuncDeclaration().accept(typeSetter);
                return fItem.getReturnType();
            }catch (ItemNotFoundException e) {
            }
        }
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        try {
            FunctionSymbolTableItem funcSym = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + identifier.getName());
            return new FptrType(identifier.getName());

        }catch (ItemNotFoundException e) {
            try{
                VariableSymbolTableItem varSym = (VariableSymbolTableItem) functionSymbolTable.getItem(VariableSymbolTableItem.START_KEY + identifier.getName());
                return varSym.getType();
            }catch (ItemNotFoundException e1) {

            }

        }
        return null;
    }

    @Override
    public Type visit(AnonymousFunction anonymousFunction) {
        return new FptrType(anonymousFunction.getName());
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
