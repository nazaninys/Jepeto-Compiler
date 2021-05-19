package main.compileError.typeError;

import main.compileError.CompileError;

public class AccessByIndexOnNonList extends CompileError {
    public AccessByIndexOnNonList(int line){
        super(line);
        message = "Access by index on a non-list";
    }
}
