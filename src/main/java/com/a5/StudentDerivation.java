package com.a5;

import java.util.ArrayList;
import java.util.List;

public class StudentDerivation {
    public List<Double> calculateStudent(
            int numSeg, double dblL, double c, double dof, double segmentWidth) {
        List<Double> studentArray = new ArrayList<>();
        for (int i = 0; i <= numSeg; i++) {
            double x = dblL + (i * segmentWidth);
            double density = c * Math.pow(
                    1.0 + ((x * x) / dof),
                    -(dof + 1.0) / 2.0);
            studentArray.add(density);
        }
        return studentArray;
    }
}