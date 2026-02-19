package com.shems.model;

public class SmartLight extends Device{
    public SmartLight(int id, String name, double consumption){
        super(id,name,consumption);
    }

    @Override
    public double calculateEnergyUsage(int hours){
        return hours * consumptionPerHour;
    }

    @Override
    public String toCsvLine() {
        return id + ",SmartLight," + name + "," + (isOn ? "ON" : "OFF") + "," + consumptionPerHour + ",";
    }
}
