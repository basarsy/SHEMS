package main.java.com.shems;

public class SmartLight extends Device{
    public SmartLight(int id, String name, double consumption){
        super(id,name,consumption);
    }

    @Override
    public double calculateEnergyUsage(int hours){
        return hours * consumptionPerHour;
    }
}
