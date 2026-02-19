package com.shems.model;

import com.shems.exception.MalformedDataException;

public abstract class Device {
    protected int id;
    protected String name;
    protected boolean isOn;
    protected double consumptionPerHour;

    public Device(int id, String name, double consumptionPerHour){
        this.id = id;
        this.name = name;
        this.isOn = false;
        this.consumptionPerHour = consumptionPerHour;
    }

    public abstract double calculateEnergyUsage(int hours);
    public void turnOn(){
        if (!isOn){
            isOn = true;
            System.out.println(name + " is turned on.");
        }
        else System.out.println(name + " is already on.");
    }
    public void turnOff(){
        if (isOn){
            isOn = false;
            System.out.println(name + " is turned off.");
        }
        else System.out.println(name  + " is already off.");
    }

    public void status(){
        String state = isOn ? "is running." : "is not running.";
        System.out.println("Device: " + name + " | State: " + state);
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOnSilent(boolean on) {
        this.isOn = on;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public abstract String toCsvLine();

    public static Device fromCsvLine(String[] data) throws MalformedDataException {
        if (data == null || data.length < 5) {
            throw new MalformedDataException("Insufficient columns: expected at least 5, got " + (data == null ? "null" : data.length));
        }
        try {
            int id = Integer.parseInt(data[0].trim());
            String type = data[1].trim();
            String name = data[2].trim();
            boolean isOn = "ON".equalsIgnoreCase(data[3].trim());
            if (("AirConditioner".equals(type) || "SmartTV".equals(type)) && data.length < 6) {
                throw new IllegalArgumentException("AirConditioner and SmartTV require 6 columns, got " + data.length);
            }
            double consumption = "SmartLight".equals(type)
                    ? Double.parseDouble(data[4].trim())
                    : Double.parseDouble(data[5].trim());

            return switch (type) {
            case "SmartLight" -> {
                SmartLight d = new SmartLight(id, name, consumption);
                if (isOn) d.turnOn();
                yield d;
            }
            case "AirConditioner" -> {
                AirConditioner d = new AirConditioner(id, name, consumption);
                if (data.length > 4) d.loadTemperature(Integer.parseInt(data[4]));
                if (isOn) d.turnOn();
                yield d;
            }
            case "SmartTV" -> {
                SmartTV d = new SmartTV(id, name, consumption);
                if (data.length > 4) d.setMode(data[4]);
                if (isOn) d.turnOn();
                yield d;
            }
            default -> throw new MalformedDataException("Unknown device type: " + type);
            };
        } catch (NumberFormatException e) {
            throw new MalformedDataException("Invalid number format in device data: " + String.join(",", data), e);
        }
    }
}
