package main.compileError.typeError;

import main.compileError.CompileError;

public class ListIndexIsNotAnInteger extends CompileError {
    public ListIndexIsNotAnInteger(int line){
        super(line);
        message = "List index is not an integer";
    }
}
