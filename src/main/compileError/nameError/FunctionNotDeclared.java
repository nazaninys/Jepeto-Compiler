package main.compileError.nameError;
import main.compileError.CompileError;

public class FunctionNotDeclared extends CompileError {
    public FunctionNotDeclared(int line, String functionName){
        super(line);
        message = "Function " + functionName + " not declared";
    }
}