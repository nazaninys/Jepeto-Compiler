package main.visitor;

import main.ast.nodes.declaration.*;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.*;
import main.ast.nodes.expression.values.primitive.*;
import main.ast.types.*;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.single.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.*;
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

    private void setArgumentsType(FunctionSymbolTableItem fitem, ArrayList<Type> types) {
        FunctionDeclaration fdec = fitem.getFuncDeclaration();
        if (visited.contains(fdec.getFunctionName().getName()))
            return;
        int min = Math.min(fdec.getArgs().size(), types.size());
        for (int i=0; i < min; i += 1) {
            try {
                VariableSymbolTableItem varSym = (VariableSymbolTableItem) fitem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY +  fdec.getArgs().get(i).getName());
                varSym.setType(types.get(i));
                fitem.addArgType(fdec.getArgs().get(i).getName(), types.get(i));
            }catch(ItemNotFoundException e) {

            }
        }
        if (fdec.getArgs().size() > min) {
            for(int i=min; i < fdec.getArgs().size(); i+= 1) {
                try {
                    VariableSymbolTableItem varSym = (VariableSymbolTableItem) fitem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY + fdec.getArgs().get(i).getName());
                    varSym.setType(new NoType());
                    fitem.addArgType(fdec.getArgs().get(i).getName(), new NoType());
                }catch (ItemNotFoundException e) {

                }
            }
        }
    }

    private void setArgumentsTypeWithKey(FunctionSymbolTableItem fitem, Map<String, Type> types) {
        FunctionDeclaration fdec = fitem.getFuncDeclaration();
        if (visited.contains(fdec.getFunctionName().getName()))
            return;
        for (Identifier arg: fdec.getArgs()) {
            try {
                VariableSymbolTableItem varSym = (VariableSymbolTableItem) fitem.getFunctionSymbolTable().getItem(VariableSymbolTableItem.START_KEY + arg.getName());
                if(types.containsKey(arg.getName())) {
                    varSym.setType(types.get(arg.getName()));
                    fitem.addArgType(arg.getName(), types.get(arg.getName()));
                }
                else {
                    varSym.setType(new NoType());
                    fitem.addArgType(arg.getName(), new NoType());
                }
            }catch (ItemNotFoundException e) {

            }
        }
    }

    @Override
    public Type visit(FunctionCall funcCall) {
        Type instanceType = funcCall.getInstance().accept(this);
        ArrayList<Type> types = new ArrayList<>();
        Map<String, Type> typesWithKey = new HashMap<>();
        if (instanceType instanceof FptrType) {
            for (Expression args : funcCall.getArgs()) {
                types.add(args.accept(this));
            }

            for (Map.Entry<Identifier, Expression> argsWithKey : funcCall.getArgsWithKey().entrySet()) {
                Type type = argsWithKey.getValue().accept(this);
                typesWithKey.put(argsWithKey.getKey().getName(), type);
            }
            try {
                String fKey = FunctionSymbolTableItem.START_KEY + ((FptrType) instanceType).getFunctionName();
                FunctionSymbolTableItem fItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(fKey);

                if(typesWithKey.size() != 0)
                    setArgumentsTypeWithKey(fItem, typesWithKey);
                else
                    setArgumentsType(fItem, types);

                fItem.getFuncDeclaration().accept(typeSetter);
                return fItem.getReturnType();
            }catch (ItemNotFoundException e) {//unreachable
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
