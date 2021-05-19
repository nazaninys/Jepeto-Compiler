package main.compileError.typeError;

import main.compileError.CompileError;

public class GetSizeOfNonList extends CompileError {
    public GetSizeOfNonList(int line){
        super(line);
        message = "Get size of a non-list";
    }
}