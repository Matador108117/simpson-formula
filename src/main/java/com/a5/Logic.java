package com.a5;

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
        double diference = Math.abs(simp1.calculatesimpsonIntegretion() - simp2.calculatesimpsonIntegretion());
        int i = 1;
        int numSegduplocate = numSeg * 2;
        while (diference < ERROR_FACTOR) {
            if (i == 10) {
                break;
            }
            numSegduplocate *= 2;
            if (i % 2 == 0) {
                diference = Math.abs(simp2.recalculate(numSegduplocate) - simp1.getSimsonresult());
            } else {
                diference = Math.abs(simp1.recalculate(numSegduplocate) - simp2.getSimsonresult());
            }
            i++;
        }
        OutFile out = new OutFile();
        if (i != 10) {
            System.out.println(
                    "The result for the difetence between the simpson integration and his duplicate is: " + diference);
            out.writeResultFile(List.of(String.valueOf(diference)), path, List.of("Diference"));

        } else {
            System.out.println("The result has a tolerance greater than 0.00001");
            out.writeResultFile(List.of("not reachable"), path, List.of("Diference"));
        }

    }
}
