package main;

import main.ast.nodes.Program;
import main.compileError.CompileError;
import main.visitor.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.*;

import java.util.ArrayList;

public class JepetoCompiler {

    public void compile(CharStream textStream) {
        JepetoLexer jepetoLexer = new JepetoLexer(textStream);
        CommonTokenStream tokenStream = new CommonTokenStream(jepetoLexer);

        JepetoParser jepetoParser = new JepetoParser(tokenStream);
        Program program = jepetoParser.jepeto().jepetoProgram;
        ErrorReporter errorReporter = new ErrorReporter();

        NameAnalyser nameAnalyser = new NameAnalyser();
        program.accept(nameAnalyser);
        int numberOfErrors = program.accept(errorReporter);
        if(numberOfErrors > 0)
            System.exit(1);
        TypeSetter typeSetter = new TypeSetter();
        program.accept(typeSetter);
        TypeCheker typeCheker = new TypeCheker();
        program.accept(typeCheker);
        numberOfErrors = program.accept(errorReporter);
        //ToDO compile successfull

    }

}
