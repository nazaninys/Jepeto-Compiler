package main.compileError.typeError;

import main.compileError.CompileError;

public class ConditionNotBool extends CompileError {
    public ConditionNotBool(int line) {
        super(line, "condition expression is not boolean");
    }
}
