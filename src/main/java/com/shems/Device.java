package main.java.com.shems;

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
}
