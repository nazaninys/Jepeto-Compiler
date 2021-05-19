package main.compileError.typeError;

import main.compileError.CompileError;

public class DifferentTypeOfListElements extends CompileError {
    public DifferentTypeOfListElements(int line){
        super(line);
        message = "Elements of the list have different types";
    }
}