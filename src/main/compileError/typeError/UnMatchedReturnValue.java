package main.compileError.typeError;

import main.compileError.CompileError;

public class UnMatchedReturnValue extends CompileError {
    public UnMatchedReturnValue(int line){
        super(line);
        message = "Return value does not match with method return type";
    }
}