package main.compileError.typeError;

import main.compileError.CompileError;

public class ConditionMustBeBool extends CompileError {
    public ConditionMustBeBool(int line){
        super(line);
        message = "Condition must be bool";
    }
}
