package main.compileError.nameError;
import main.compileError.CompileError;

public class VariableNotDeclared extends CompileError {
    public VariableNotDeclared(int line, String variableName){
        super(line);
        message = "Variable " + variableName + " not declared";
    }
}