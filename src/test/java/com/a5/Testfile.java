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
    }

    @Test
    public void calculatesSimpsonIntegrationForImageExampleCreatesExpectedStudentValues() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.calculatesimpsonIntegretion();
        assertEquals(11, integration.getStudentArray().size());
    }

    @Test
    public void recalculatesUsingNewNumberOfSegments() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.recalculate(20);
        assertEquals(20, integration.getNumSeg());
    }

    @Test
    public void recalculatesUsingNewNumberOfSegmentsCreatesExpectedStudentValues() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.recalculate(20);
        assertEquals(21, integration.getStudentArray().size());
    }

    @Test
    public void recalculatesUsingNewNumberOfSegmentsReturnsExpectedResult() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        double result = integration.recalculate(20);
        assertEquals(0.3500586368972011, result, 1e-12);
    }

    @Test
    public void getsAndSetsNumSeg() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setNumSeg(20);
        assertEquals(20, integration.getNumSeg());
    }

    @Test
    public void getsAndSetsDblL() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setDblL(-1.0);
        assertEquals(-1.0, integration.getDblL(), 0.0);
    }

    @Test
    public void getsAndSetsDblH() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setDblH(2.0);
        assertEquals(2.0, integration.getDblH(), 0.0);
    }

    @Test
    public void getsAndSetsDof() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setDof(12.0);
        assertEquals(12.0, integration.getDof(), 0.0);
    }

    @Test
    public void getsAndSetsDblX() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        List<Double> xValues = List.of(1.0, 2.0);
        integration.setDblX(xValues);
        assertEquals(xValues, integration.getDblX());
    }

    @Test
    public void getsAndSetsSegmentsWidth() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setSecmentsWidth(0.15);
        assertEquals(0.15, integration.getSecmentsWidth(), 0.0);
    }

    @Test
    public void getsAndSetsGamma() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        double[] gammaValues = {5.0, 6.0};
        integration.setGamma(gammaValues);
        assertEquals(gammaValues, integration.getGamma());
    }

    @Test
    public void getsAndSetsC() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setC(0.25);
        assertEquals(0.25, integration.getC(), 0.0);
    }

    @Test
    public void getsAndSetsStudentArray() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        List<Double> studentValues = List.of(3.0, 4.0);
        integration.setStudentArray(studentValues);
        assertEquals(studentValues, integration.getStudentArray());
    }

    @Test
    public void getsAndSetsSimpsonResult() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.1, 9.0, 10);
        integration.setSimsonresult(0.75);
        assertEquals(0.75, integration.getSimsonresult(), 0.0);
    }

    @Test
    public void calculatesGammaForHalfValue() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.0, 9.0, 2);
        assertEquals(Math.sqrt(Math.PI), integration.gamma(0.5), 1e-12);
    }

    @Test
    public void calculatesGammaForWholeValue() {
        SimpsonIntegration integration = new SimpsonIntegration(0.0, 1.0, 9.0, 2);
        assertEquals(2.0, integration.gamma(3.0), 1e-12);
    }

    @Test
    public void refinesIntegrationWhenInitialDifferenceIsTooLarge() throws Exception {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));
        new Logic().run(0.0, 1.0, 1.0, 1);
        assertTrue(capturedOutput.toString().contains("The result"));
    }

    @Test
    public void refinesIntegrationCreatesResultFile() throws Exception {
        new Logic().run(0.0, 1.0, 1.0, 1);
        assertTrue(Files.exists(resultsPath));
    }

    @Test
    public void stopsRefiningAfterMaximumIterations() throws Exception {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));
        new Logic().run(0.0, 100.0, 0.01, 1);
        assertTrue(capturedOutput.toString().contains("The result"));
    }

    @Test
    public void stopsRefiningCreatesResultFile() throws Exception {
        new Logic().run(0.0, 100.0, 0.01, 1);
        assertTrue(Files.exists(resultsPath));
    }

    @Test
    public void testFor5aDocsFirstExample() throws Exception {
        assertMainResult(new String[] {"0", "1.1", "9", "10"}, "0.35006");
    }

    @Test
    public void testFor5aDocsSecondExample() throws Exception {
        assertMainResult(new String[] {"0", "1.1812", "10", "10"}, "0.36757");
    }

    @Test
    public void testFor5aDocsThirdExample() throws Exception {
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
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithValue() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(List.of("0.35006"), output.toString(), List.of("Result"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("0.35006"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithDifferentHeaderAndValueLengths() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("first", "second"),
                output.toString(),
                List.of("Only header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("Only header"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithDifferentHeaderAndValueLengthsWritesFirstValue() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("first", "second"),
                output.toString(),
                List.of("Only header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("first"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithDifferentHeaderAndValueLengthsWritesSecondValue() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("first", "second"),
                output.toString(),
                List.of("Only header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("second"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithMoreHeadersThanValues() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("value"),
                output.toString(),
                List.of("First header", "Second header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("First header"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithMoreHeadersThanValuesWritesSecondHeader() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("value"),
                output.toString(),
                List.of("First header", "Second header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("Second header"));
        Files.deleteIfExists(output);
    }

    @Test
    public void writesResultFileWithMoreHeadersThanValuesWritesValue() throws Exception {
        Path output = Files.createTempFile("a5-test-", ".txt");
        new OutFile().writeResultFile(
                List.of("value"),
                output.toString(),
                List.of("First header", "Second header"));
        String contents = Files.readString(output);
        assertTrue(contents.contains("value"));
        Files.deleteIfExists(output);
    }

    @Test
    public void reportsFileWritingErrors() {
        assertThrows(RuntimeException.class, () -> new OutFile().writeResultFile(
                List.of("value"),
                ".",
                List.of("Result")));
    }

    @Test
    public void coversMainConstructor() {
        assertTrue(new Main() instanceof Main);
    }

    @Test
    public void reportsWhenToleranceCannotBeReached() throws Exception {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));
        new Logic().run(0.0, 1.0, 1000.0, 1);
        assertTrue(capturedOutput.toString().contains(
                "The result has a tolerance greater than 0.00001"));
    }

    @Test
    public void reportsWhenToleranceCannotBeReachedWritesExplanation() throws Exception {
        new Logic().run(0.0, 1.0, 1000.0, 1);

        assertTrue(Files.readString(resultsPath).contains("not reachable"));
    }

    private void assertMainResult(String[] arguments, String expectedResult) throws Exception {
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));
        Main.main(arguments);
        String consoleOutput = capturedOutput.toString();
        String fileOutput = Files.readString(resultsPath);
        assertTrue(consoleOutput.contains(expectedResult) && fileOutput.contains(expectedResult));
    }
}
