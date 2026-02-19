package com.shems.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shems.model.Device;

public class EnergyService {

    public double calculateTotalEnergyUsage(Device[] devices, int hours){
        return Arrays.stream(devices)
        .mapToDouble(d -> d.calculateEnergyUsage(hours))
        .sum();
    }

    public List<Device> findHighConsumers(Device[] devices){
        return Arrays.stream(devices)
        .filter(device -> device.calculateEnergyUsage(1) > 100)
        .collect(Collectors.toList());
    }

    public List<Device> groupDevicesByType(Device[] devices){
        return Arrays.stream(devices)
        .sorted(Comparator.comparingDouble(d -> d.calculateEnergyUsage(1)))
        .collect(Collectors.toList());
    }

    public Map<String, Long> countDevicesByType(Device[] devices){
        return Arrays.stream(devices)
        .collect(Collectors.groupingBy(Device::getType, Collectors.counting()));
    }
}
