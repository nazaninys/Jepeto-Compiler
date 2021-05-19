package main.compileError.typeError;

import main.compileError.CompileError;

public class UseReturnValueOfVoid extends CompileError {
    public UseReturnValueOfVoid(int line){
        super(line);
        message = "Cannot use return value of a function having void return type";
    }
}
