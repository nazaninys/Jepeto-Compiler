package main.compileError.typeErrors;

import main.compileError.CompileError;

public class ListElementsTypeNotMatch extends CompileError {
    public ListElementsTypeNotMatch(int line) {
        super(line, "List elements type do not match");
    }
}
