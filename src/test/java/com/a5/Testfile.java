package com.a5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.a5.reuse.OutFile;

public class Testfile {
    private final Path resultsPath = Path.of("results.txt");
    private PrintStream originalOutput;

    @Before
    public void setUp() throws Exception {
        originalOutput = System.out;
        Files.deleteIfExists(resultsPath);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOutput);
        Files.deleteIfExists(resultsPath);
    }

    @Test
    public void calculatesSegmentWidth() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);

        assertEquals(0.11, integration.calculateSegmentheight(), 1e-12);
    }

    @Test
    public void calculatesStudentDistributionConstant() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);

        integration.calculateSegmentheight();
        double constant = integration.calculateC();

        assertEquals(0.3880349088716687, constant, 1e-12);
    }

    @Test
    public void calculatesSimpsonIntegrationForImageExample() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);

        double result = integration.calculatesimpsonIntegretion();

        assertEquals(0.3500589042865572, result, 1e-12);
        assertEquals(11, integration.getStudentArray().size());
    }

    @Test
    public void recalculatesUsingNewNumberOfSegments() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);

        double result = integration.recalculate(20);

        assertEquals(20, integration.getNumSeg());
        assertEquals(21, integration.getStudentArray().size());
        assertEquals(0.3500586368972011, result, 1e-12);
    }

    @Test
    public void testfor5adocs() throws Exception {
        assertMainResult(new String[] {"0", "1.1", "9", "10"}, "0.35006");
        assertMainResult(new String[] {"0", "1.1812", "10", "10"}, "0.36757");
        assertMainResult(new String[] {"0", "2.750", "30", "10"}, "0.49500");
    }

    @Test
    public void mainRejectsMissingArguments() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Main.main(new String[] {"0", "1.1"}));
    }

    @Test
    public void writesResultFileWithHeaderAndValue() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");

        new OutFile().writeResultFile(List.of("0.35006"), output.toString(), List.of("Result"));

        String contents = Files.readString(output);
        assertTrue(contents.contains("Result"));
        assertTrue(contents.contains("0.35006"));
        Files.deleteIfExists(output);
    }

    private void assertMainResult(String[] arguments, String expectedResult) throws Exception {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));

        Main.main(arguments);

        String consoleOutput = capturedOutput.toString();
        String fileOutput = Files.readString(resultsPath);
        assertTrue(consoleOutput.contains(expectedResult));
        assertTrue(fileOutput.contains(expectedResult));
    }
}
