package com.shems.model;

public class SmartTV extends Device {
    private String mode = "NORMAL";

    public SmartTV(int id, String name, double consumption) {
        super(id, name, consumption);
    }

    public void setMode(String mode) {
        if ("ECO".equals(mode) || "NORMAL".equals(mode) || "HIGH".equals(mode)) {
            this.mode = mode;
        }
    }

    @Override
    public double calculateEnergyUsage(int hours) {
        return switch (mode) {
            case "ECO" -> hours * consumptionPerHour * 0.5;
            case "HIGH" -> hours * consumptionPerHour * 1.5;
            default -> hours * consumptionPerHour;
        };
    }

    @Override
    public String toCsvLine() {
        return id + ",SmartTV," + name + "," + (isOn ? "ON" : "OFF") + "," + mode + "," + consumptionPerHour;
    }
}
