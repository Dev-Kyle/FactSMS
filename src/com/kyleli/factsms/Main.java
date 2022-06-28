package com.kyleli.factsms;

public class Main {
    public static void main(String[] args) {
        FactGenerator gen = new FactGenerator(FactGenerator.LANG.EN);
        try {
            System.out.println(gen.getFact());
        } catch (Exception e) {
            System.out.println("Failed to get fact: " + e.getMessage());
        }
    }
}
