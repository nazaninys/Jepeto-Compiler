package main.compileError.typeError;

import main.compileError.CompileError;

public class CallingANonCallable extends CompileError {
    public CallingANonCallable(int line){
        super(line);
        message = "Calling a non-callable";
    }
}
