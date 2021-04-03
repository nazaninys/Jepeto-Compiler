package main;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class JepetoTest {
    private JepetoRunner jp;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String inputFile;
    private String outputFile;
    private String[] args = {"main.grammar.Jepeto", "jepeto" ,"samples/1.jp"};
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        jp = new JepetoRunner();
    }

    public JepetoTest(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        String path = "samples/";
        List<String[]> elements = new ArrayList<>();
        for(Integer i=1; i < 3; i ++)
            elements.add(new String[]{path + i.toString() + ".jp", path + "ans-" + i.toString() + ".jp"});
        return elements;
    }

    @Test
    public void parse() throws Exception {
        args[2] = inputFile;
        jp.run(args);
        Assert.assertEquals( outputStreamCaptor.toString().trim(), Files.readString(Path.of(outputFile)));

    }
}
