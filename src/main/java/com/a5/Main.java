package com.a5;

public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();
        logic.run(Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2]),Integer.parseInt(args[3]));
        
    }
}