package com.shems;

import com.shems.model.AirConditioner;
import com.shems.model.House;
import com.shems.model.SmartLight;
import com.shems.model.SmartTV;
import com.shems.service.DeviceSimulator;

public class Main {
    public static void main(String[] args) {
        House house = new House();
        house.loadDevicesFromFile(house.filePath);

        if (house.devices.isEmpty()) {
            house.addDevice(new SmartLight(1, "Living Room Light", 0.05));
            house.addDevice(new SmartLight(2, "Bedroom Light", 0.04));
            house.addDevice(new AirConditioner(3, "Living Room AC", 1.2));
            house.addDevice(new SmartTV(4, "Living Room TV", 0.15));
        }

        DeviceSimulator simulator = new DeviceSimulator(house.devices);
        Thread simulatorThread = new Thread(simulator, "DeviceSimulator");
        simulatorThread.setDaemon(true);
        simulatorThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(simulator::stop));

        try {
            while (true) {
                Thread.sleep(5_000);
                double bill = house.calculateCurrentEnergyBill(1);
                System.out.printf("--- Current Energy Bill (1h rate): %.2f kWh ---%n", bill);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            simulator.stop();
        }
    }
}
