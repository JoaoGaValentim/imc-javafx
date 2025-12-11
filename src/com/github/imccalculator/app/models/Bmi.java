package com.github.imccalculator.app.models;

public class Bmi {
    private final double height;
    private final double weight;

    public Bmi(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getBmiResult() {
        return weight / (height * height);
    }
}
