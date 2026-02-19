package com.shems.model;

public class AirConditioner extends Device{
    private int temperature;
    private final double highConsumptionRate = 0.1;
    public AirConditioner(int id, String name, double consumption){
        super(id, name, consumption);
        this.temperature = 24;
    }

    void loadTemperature(int temperature) {
        if (temperature >= 16 && temperature <= 32) this.temperature = temperature;
    }

    public void setTemperature(int temperature){
        if (temperature < 16 || temperature > 32 ){
            System.out.println("Invalid temperature! Please enter a temperature between 16-32");
        }
        else if (this.temperature == temperature){
            System.out.println("It's already " + temperature + " degrees.");
        }
        else {
            this.temperature = temperature;
            System.out.println(name + " temperature has been set to " + temperature);
        }
    }

    @Override
    public double calculateEnergyUsage(int hours){
        double baseConsumption = hours * consumptionPerHour;
        if (temperature < 24){
            int degreeDifference = 24 - temperature;
            double increaseMultiplier = 1 + (degreeDifference * highConsumptionRate);
            return baseConsumption * increaseMultiplier;
        }
        else if (temperature <= 50){
            int degreeDifference = 50 - temperature;
            double increaseMultiplier = 1 + (degreeDifference * highConsumptionRate);
            return baseConsumption * increaseMultiplier;
        }
        else return baseConsumption;
    }

    @Override
    public void status(){
        System.out.println(name + " | Current temperature: " + temperature + "°C");
    }

    @Override
    public String toCsvLine() {
        return id + ",AirConditioner," + name + "," + (isOn ? "ON" : "OFF") + "," + temperature + "," + consumptionPerHour;
    }
}
