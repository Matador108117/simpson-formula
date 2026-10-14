package com.a5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.a5.reuse.OutFile;

public class Logic {
    private SimpsonIntegration simp1;
    private SimpsonIntegration simp2;
    private String path = "./results.txt";
    public final double ERROR_FACTOR = 0.00001;

    public void run(double limless, double limhigh, double dof, int numSeg) {
        simp1 = new SimpsonIntegration(limless, limhigh, dof, numSeg);
        simp2 = new SimpsonIntegration(limless, limhigh, dof, (numSeg * 2));
        double result = simp1.calculatesimpsonIntegretion();
        double finerResult = simp2.calculatesimpsonIntegretion();
        double diference = Math.abs(result - finerResult);
        int i = 1;
        int numSegduplocate = numSeg * 2;
        while (diference > ERROR_FACTOR && i < 10) {
            numSegduplocate *= 2;
            result = simp2.recalculate(numSegduplocate);
            diference = Math.abs(result - finerResult);
            finerResult = result;
            i++;
        }
        OutFile out = new OutFile();
        if (diference <= ERROR_FACTOR) {
            String roundedResult = BigDecimal.valueOf(result)
                    .setScale(5, RoundingMode.HALF_UP)
                    .toPlainString();
            System.out.println(
                    "The result of the Simpson integration is: " + roundedResult);
            out.writeResultFile(List.of(roundedResult), path, List.of("Result"));

        } else {
            System.out.println("The result has a tolerance greater than 0.00001");
            out.writeResultFile(List.of("not reachable"), path, List.of("Result"));
        }

    }
}
