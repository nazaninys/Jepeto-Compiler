package main.compileError.typeError;

import main.compileError.CompileError;

public class OperatorsNotMatch extends CompileError {
    public OperatorsNotMatch(int line) {
        super(line, "operators don't match");
    }
}
