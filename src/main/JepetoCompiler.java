package main;

import main.ast.nodes.Program;
import main.visitor.ASTTreePrinter;
import main.visitor.ErrorReporter;
import main.visitor.NameAnalyser;
import main.visitor.Visitor;
import main.visitor.typeVisitor.TypeMatch;
import main.visitor.typeVisitor.TypeSetter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.*;

public class JepetoCompiler {
    public void compile(CharStream textStream) {
        JepetoLexer jepetoLexer = new JepetoLexer(textStream);
        CommonTokenStream tokenStream = new CommonTokenStream(jepetoLexer);

        JepetoParser jepetoParser = new JepetoParser(tokenStream);
        Program program = jepetoParser.jepeto().jepetoProgram;
//        ErrorReporter errorReporter = new ErrorReporter();
        NameAnalyser nameAnalyser = new NameAnalyser();
        program.accept(nameAnalyser);
        if (nameAnalyser.getError() == 0) {
//            TypeMatch typeMatch = new TypeMatch();
//            program.accept(typeMatch);
//            int numberOfErrors = program.accept(errorReporter);
//            if (numberOfErrors > 0)
//                System.exit(1);
//            TypeSetter typeSetter = new TypeSetter();
//            program.accept(typeSetter);
            Visitor<Void> treePrinter = new ASTTreePrinter();
            program.accept(treePrinter);
        }

    }

}
