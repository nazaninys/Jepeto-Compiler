package main.compileError.typeError;

import main.compileError.CompileError;

public class UnmatchedArgumentType extends CompileError {
    public UnmatchedArgumentType(int line){
        super(line);
        message = "Args in function call do not match with definition";
    }
}