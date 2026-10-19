package com.a5;
public class Constant {
    public double calculateC(double dof, double[] gamma){
        Gamma g = new Gamma();   
        gamma[0] = g.gamma((dof + 1.0) / 2.0);
           gamma[1] = g.gamma(dof / 2.0);
          double c = gamma[0] / (Math.sqrt(dof * Math.PI) * gamma[1]);
        return c;
    }
}
