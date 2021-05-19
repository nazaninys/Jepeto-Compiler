package main.visitor;

import main.ast.nodes.declaration.FunctionDeclaration;
import main.ast.nodes.expression.AnonymousFunction;
import main.ast.nodes.expression.Expression;
import main.ast.nodes.expression.FunctionCall;
import main.ast.nodes.expression.Identifier;
import main.ast.nodes.expression.values.VoidValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.types.NoType;
import main.ast.types.Type;
import main.ast.types.VoidType;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.single.BoolType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.AnonymousSymbolTableItem;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.symbolTable.items.SymbolTableItem;
import main.symbolTable.items.VariableSymbolTableItem;

import java.util.*;

public class TypeInference extends Visitor<Type> {
    private SymbolTable functionSymbolTable;
    private Set<String> visited;
    private TypeSetter typeSetter;
    private int numOfAnonymous = 0;

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
        Type instaceType = funcCall.getInstance().accept(this);
        if (instaceType instanceof FptrType) {
            try {
                FunctionSymbolTableItem fitem = (FunctionSymbolTableItem) SymbolTable.root.getItem(FunctionSymbolTableItem.START_KEY + ((FptrType) instaceType).getFunctionName());

                ArrayList<Type> types = new ArrayList<>();
                for (Expression args : funcCall.getArgs())
                    types.add(args.accept(this));
                Map<String, Type> typesWithKey = new HashMap<>();
                for (Map.Entry<Identifier, Expression> argsWithKey : funcCall.getArgsWithKey().entrySet()) {
                    Type type = argsWithKey.getValue().accept(this);
                    typesWithKey.put(argsWithKey.getKey().getName(), type);


                }
                if(typesWithKey.size() != 0)
                    setArgumentsTypeWithKey(fitem, typesWithKey);
                else
                    setArgumentsType(fitem, types);
                fitem.getFuncDeclaration().accept(typeSetter);
                return fitem.getReturnType();
            }catch (ItemNotFoundException e) {
                try {

                    AnonymousSymbolTableItem anonymousItem = (AnonymousSymbolTableItem) SymbolTable.root.getItem(
                            AnonymousSymbolTableItem.START_KEY + ((FptrType) instaceType).getFunctionName());
                }catch (ItemNotFoundException e1) {


                }

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
        numOfAnonymous += 1;
        return new FptrType(String.valueOf(numOfAnonymous));
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
