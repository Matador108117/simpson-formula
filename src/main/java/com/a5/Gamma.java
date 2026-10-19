package com.a5;

public class Gamma {
    public double gamma(double z) {
    double result = 1.0;
    if (z % 1 == 0) {
        for (int i = 1; i < z; i++) {
            result *= i;
        }
        return result;
    } else {
        double fraction = z - (int) z;
        if (Math.abs(fraction - 0.5) < 0.0000000001) {
            while (z > 0.5) {
                z -= 1.0;
                result *= z;
            }
            return result * Math.sqrt(Math.PI);
        }
        return result;
    }
    }
}
