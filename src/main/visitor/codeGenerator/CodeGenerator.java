package main.visitor.codeGenerator;

import main.ast.nodes.*;
import main.ast.nodes.declaration.*;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.*;
import main.ast.nodes.expression.values.primitive.*;
import main.ast.nodes.statement.*;
import main.ast.types.*;
import main.ast.types.functionPointer.*;
import main.ast.types.list.*;
import main.ast.types.single.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.visitor.Visitor;
import main.visitor.type.ExpressionTypeChecker;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CodeGenerator extends Visitor<String> {
    private final String outputPath;
    private FileWriter mainFile;
    private final ExpressionTypeChecker expressionTypeChecker;
    private Set<String> visited;
    private int numOfUsedTemp = 0;
    private int numOfUsedLabel = 0;
    private FunctionDeclaration curFuncDec;

    public CodeGenerator(ExpressionTypeChecker expressionTypeChecker, Set<String> visited) {
        this.expressionTypeChecker = expressionTypeChecker;
        this.visited = visited;
        outputPath = "./output/";
        prepareOutputFolder();
    }

    private void prepareOutputFolder() {
        String jasminPath = "utilities/jarFiles/jasmin.jar";
        String listClassPath = "utilities/codeGenerationUtilityClasses/List.j";
        String fptrClassPath = "utilities/codeGenerationUtilityClasses/Fptr.j";
        try{
            File directory = new File(this.outputPath);
            File[] files = directory.listFiles();
            if(files != null)
                for (File file : files)
                    file.delete();
             directory.mkdir();
        }
        catch(SecurityException e) {//unreachable
        }
        copyFile(jasminPath, this.outputPath + "jasmin.jar");
        copyFile(listClassPath, this.outputPath + "List.j");
        copyFile(fptrClassPath, this.outputPath + "Fptr.j");

        try {
            String path = outputPath + "Main.j";
            File file = new File(path);
            file.createNewFile();
            mainFile = new FileWriter(path);
        } catch (IOException e) {//unreachable
        }
    }

    private void copyFile(String toBeCopied, String toBePasted) {
        try {
            File readingFile = new File(toBeCopied);
            File writingFile = new File(toBePasted);
            InputStream readingFileStream = new FileInputStream(readingFile);
            OutputStream writingFileStream = new FileOutputStream(writingFile);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = readingFileStream.read(buffer)) > 0)
                writingFileStream.write(buffer, 0, readLength);
            readingFileStream.close();
            writingFileStream.close();
        } catch (IOException e) {//never reached
        }
    }

    private void addCommand(String command) {
        try {
            command = String.join("\n\t\t", command.split("\n"));
            if(command.startsWith("Label_"))
                mainFile.write("\t" + command + "\n");
            else if(command.startsWith("."))
                mainFile.write(command + "\n");
            else
                mainFile.write("\t\t" + command + "\n");
            mainFile.flush();
        } catch (IOException e) {//never reached

        }
    }

    private void addStaticMainMethod() {
        addCommand(".method public static main([Ljava/lang/String;)V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");
        addCommand("new Main");
        addCommand("invokespecial Main/<init>()V");
        addCommand("return");
        addCommand(".end method");
    }

    private int slotOf(String identifier) {
        int count = 1;
        if (curFuncDec != null){
            for(Identifier arg : curFuncDec.getArgs()){
                if(arg.getName().equals(identifier))
                    return count;
                count++;
            }
        }
        if (identifier.equals("")){
            int temp = numOfUsedTemp;
            numOfUsedTemp++;
            return count + temp;
        }
        return 0;
    }

    private String makeTypeSignature(Type t) {
        if (t instanceof IntType)
            return "java/lang/Integer";
        if (t instanceof BoolType)
            return "java/lang/Boolean";
        if (t instanceof StringType)
            return "java/lang/String";
        if (t instanceof ListType)
            return "List";
        if (t instanceof FptrType)
            return "Fptr";
        return null;
    }

    private String getFreshLabel(){
        String label = "Label_";
        label += numOfUsedLabel;
        numOfUsedLabel++;
        return label;
    }

    private String primitiveToNonPrimitive(Type type){
        String commands = "";
        if(type instanceof IntType)
            commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
        if(type instanceof BoolType)
            commands += "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;\n";
        return commands;
    }

    private String nonPrimitiveToPrimitive(Type type){
        String commands = "";
        if(type instanceof IntType)
            commands += "invokevirtual java/lang/Integer/intValue()I\n";
        if(type instanceof BoolType)
            commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
        return commands;
    }

    @Override
    public String visit(Program program) {
        addCommand(".class Main");
        addCommand(".super java/lang/Object");

        addStaticMainMethod();

        program.getMain().accept(this);

        for(FunctionDeclaration funcDec : program.getFunctions()){
            if (! visited.contains(funcDec.getFunctionName().getName()))
                continue;
            curFuncDec = funcDec;
            funcDec.accept(this);
            numOfUsedTemp = 0;
        }
        return null;
    }

    @Override
        public String visit(FunctionDeclaration funcDeclaration) {
        String searchKey = FunctionSymbolTableItem.START_KEY + funcDeclaration.getFunctionName().getName();
        Map<String, Type> argTypes = new LinkedHashMap<>();
        Type returnType = new VoidType();
        try {
            FunctionSymbolTableItem functionSymbolTableItem = (FunctionSymbolTableItem) SymbolTable.root.getItem(searchKey);
            argTypes = functionSymbolTableItem.getArgTypes();
            returnType = functionSymbolTableItem.getReturnType();
        }catch (ItemNotFoundException e){
        }

        String header = "";
        header += ".method public " + funcDeclaration.getFunctionName().getName() + "(";
        for(Identifier arg : funcDeclaration.getArgs()){
            header += "L" + makeTypeSignature(argTypes.get(arg.getName())) + ";";
        }
        if (returnType instanceof VoidType)
            header += ")V";
        else
            header += ")L"  + makeTypeSignature(returnType) + ";";

        addCommand(header);
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");

        funcDeclaration.getBody().accept(this);
        addCommand(".end method");

        return null;
    }

    @Override
    public String visit(MainDeclaration mainDeclaration) {
        addCommand(".method public <init>()V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");
        addCommand("aload 0");
        addCommand("invokespecial java/lang/Object/<init>()V");

        mainDeclaration.getBody().accept(this);

        addCommand("return");
        addCommand(".end method");
        return null;
    }


    @Override
    public String visit(BlockStmt blockStmt) {
        for (Statement statement: blockStmt.getStatements()) {
            statement.accept(this);
        }
        return null;
    } //done

    @Override
    public String visit(ConditionalStmt conditionalStmt) {
        String labelFalse = getFreshLabel();
        String labelAfter = getFreshLabel();
        addCommand(conditionalStmt.getCondition().accept(this));
        addCommand(nonPrimitiveToPrimitive(new BoolType()));
        addCommand("ifeq " + labelFalse);
        conditionalStmt.getThenBody().accept(this);
        addCommand("goto " + labelAfter);
        addCommand(labelFalse + ":");
        if (conditionalStmt.getElseBody() != null)
            conditionalStmt.getElseBody().accept(this);
        addCommand(labelAfter + ":");
        return null;
    }

    @Override
    public String visit(FunctionCallStmt funcCallStmt) {
        expressionTypeChecker.setFunctioncallStmt(true);
        addCommand(funcCallStmt.getFunctionCall().accept(this));
        addCommand("pop");
        expressionTypeChecker.setFunctioncallStmt(false);
        return null;
    }

    @Override
    public String visit(PrintStmt print) {
        addCommand("getstatic java/lang/System/out Ljava/io/PrintStream;");
        Type argType = print.getArg().accept(expressionTypeChecker);
        addCommand(print.getArg().accept(this));
        addCommand(nonPrimitiveToPrimitive(argType));
        if (argType instanceof IntType)
            addCommand("invokevirtual java/io/PrintStream/println(I)V");
        if (argType instanceof BoolType)
            addCommand("invokevirtual java/io/PrintStream/println(Z)V");
        if (argType instanceof StringType)
            addCommand("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");

        //todo for list printing
        return null;
    }

    @Override
    public String visit(ReturnStmt returnStmt) {
        Type type = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
        if(type instanceof VoidType) {
            addCommand("return");
        }
        else {
            addCommand( returnStmt.getReturnedExpr().accept(this) );
            addCommand("areturn");
        }
        return null;
    } //done

    @Override
    public String visit(BinaryExpression binaryExpression) {
        //todo
        return null;
    }

    @Override
    public String visit(UnaryExpression unaryExpression) {
        //todo
        return null;
    }

    @Override
    public String visit(AnonymousFunction anonymousFunction) {
        //todo
        return null;
    }

    @Override
    public String visit(Identifier identifier) {
        String commands = "";
        String searchKey = FunctionSymbolTableItem.START_KEY + identifier.getName();
        int slotOfArgument;
        try {
            SymbolTable.root.getItem(searchKey);
            commands += "new Fptr\n";
            commands += "dup\n";
            commands += "aload 0\n";
            commands += "ldc \"" + identifier.getName() + "\"\n";
            commands += "invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V\n";
        }catch (ItemNotFoundException e){
            slotOfArgument = slotOf(identifier.getName());
            commands += "aload " + slotOfArgument + "\n";
        }
        return  commands;
    }

    @Override
    public String visit(ListAccessByIndex listAccessByIndex) {
        //todo
        return null;
    }

    @Override
    public String visit(ListSize listSize) {
        String commands = "";
        commands += listSize.getInstance().accept(this);
        commands += "invokevirtual List/getSize()I\n";
        commands += primitiveToNonPrimitive(new IntType());
        return commands;
    }

    @Override
    public String visit(FunctionCall funcCall) {
        String commands = "";
        int tempIndex = slotOf("");

        FptrType fptrType = (FptrType) funcCall.getInstance().accept(expressionTypeChecker);
        String searchKey = FunctionSymbolTableItem.START_KEY + fptrType.getFunctionName();
        Type retType = new VoidType(); // just for initialize

        try {
            FunctionSymbolTableItem functionSymbolTableItem = (FunctionSymbolTableItem)SymbolTable.root.getItem(searchKey);
            retType = functionSymbolTableItem.getReturnType();
        }catch (ItemNotFoundException e){ //unreachable
        }

        commands += funcCall.getInstance().accept(this);
        commands += "new java/util/ArrayList\n";
        commands += "dup\n";
        commands += "invokespecial java/util/ArrayList/<init>()V\n";
        commands += "astore " + tempIndex + "\n";


        for(Expression arg : funcCall.getArgs()){
            commands += "aload " + tempIndex + "\n";

            Type argType = arg.accept(expressionTypeChecker);

            if(argType instanceof ListType) {
                commands += "new List\n";
                commands += "dup\n";
            }

            commands += arg.accept(this);

            if(argType instanceof ListType) {
                commands += "invokespecial List/<init>(LList;)V\n";
            }
            commands += "invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z\n";
            commands += "pop\n";
        }

        commands += "aload " + tempIndex + "\n";
        commands += "invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;\n";

        if(!(retType instanceof VoidType))
            commands += "checkcast " + makeTypeSignature(retType) + "\n";

        return commands;
    }

    @Override
    public String visit(ListValue listValue) {
        String commands = "";
        int tempIndex = slotOf("");
        ArrayList<Expression> elements = listValue.getElements();

        commands += "new List\n";
        commands += "dup\n";
        commands += "new java/util/ArrayList\n";
        commands += "dup\n";
        commands += "invokespecial java/util/ArrayList/<init>()V\n";
        commands += "astore " + tempIndex + "\n";
        for (Expression element: elements) {
            commands += "aload " + tempIndex + "\n";
            commands += element.accept(this);
            commands += "invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z\n";
            commands += "pop\n";
        }
        commands += "aload " + tempIndex + "\n";
        commands += "invokespecial List/<init>(Ljava/util/ArrayList;)V\n";
        return commands;
    }

    @Override
    public String visit(IntValue intValue) {
        String commands = "";
        commands += "ldc " + intValue.getConstant() +"\n";
        commands += primitiveToNonPrimitive(new IntType());
        return commands;
    } //done

    @Override
    public String visit(BoolValue boolValue) {
        String commands = "";
        if(boolValue.getConstant())
            commands += "ldc " + "1\n";
        else
            commands += "ldc " + "0\n";
        commands += primitiveToNonPrimitive(new BoolType());
        return commands;
    } //done

    @Override
    public String visit(StringValue stringValue) {
        String commands = "";
        commands += "ldc \"" + stringValue.getConstant() + "\"\n";
        return commands;
    } //done

    @Override
    public String visit(VoidValue voidValue) {
        return "";
    }
}
