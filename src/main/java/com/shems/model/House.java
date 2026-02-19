package com.shems.model;

import java.util.ArrayList;

import com.shems.repository.DeviceRepository;

public class House {
    public ArrayList<Device> devices;
    public String filePath = "src/main/resources/devices.csv";
    private final DeviceRepository deviceRepository = new DeviceRepository();

    public void saveDevicesToFile(String filePath) {
        if (devices != null) {
            deviceRepository.saveToFile(filePath, devices);
        }
    }

    public void loadDevicesFromFile(String filePath) {
        devices = new ArrayList<>(deviceRepository.loadFromFile(filePath));
    }

    public void addDevice(Device device){
        devices.add(device);
    }
    public void removeDevice(Device device){
        devices.remove(device);
    }
    public Device getDevice(int id){
        return devices.get(id);
    }
    public Device[] getAllDevices(){
        return devices.toArray(new Device[0]);
    }

    public double calculateCurrentEnergyBill(int hours) {
        if (devices == null) return 0;
        return devices.stream()
                .filter(Device::isOn)
                .mapToDouble(d -> d.calculateEnergyUsage(hours))
                .sum();
    }
}
