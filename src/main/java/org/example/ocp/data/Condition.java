package org.example.ocp.data;

public enum Condition {
    HOT(30),
    WARM(20),
    COLD(10);

    private final double temperature;

    Condition(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
