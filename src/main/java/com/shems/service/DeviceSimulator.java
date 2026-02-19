package com.shems.service;

import java.util.List;
import java.util.Random;

import com.shems.model.Device;

public class DeviceSimulator implements Runnable {
    private final List<Device> devices;
    private final Random random = new Random();
    private volatile boolean running = true;

    public DeviceSimulator(List<Device> devices) {
        this.devices = devices;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running && !devices.isEmpty()) {
            Device device = devices.get(random.nextInt(devices.size()));
            device.setOnSilent(!device.isOn());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
