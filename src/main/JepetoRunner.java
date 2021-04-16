package main;

import org.antlr.v4.gui.TestRig;
import org.antlr.v4.runtime.*;
import parsers.JepetoLexer;
import parsers.JepetoParser;

import java.io.IOException;

public class JepetoRunner {

    public void run(String[] args) throws Exception {
        TestRig.main(args);
    }

    public static void main(String[] args) throws Exception {
        CharStream reader = CharStreams.fromFileName("samples/condition/parse/1.jp");
        JepetoLexer jepetoLexer = new JepetoLexer(reader);
        CommonTokenStream tokenStream = new CommonTokenStream(jepetoLexer);
        JepetoParser jepetoParser = new JepetoParser(tokenStream);
    }

}