package main.compileError.nameError;
import main.compileError.CompileError;

public class DuplicateFunction extends CompileError {
    public DuplicateFunction(int line, String functionName){
        super(line);
        message = "Duplicate function " + functionName;
    }
}
