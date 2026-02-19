package com.shems.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.shems.exception.MalformedDataException;
import com.shems.model.Device;

public class DeviceRepository {
    private static final String CSV_HEADER = "ID,Type,Name,Status,SpecificParam1,SpecificParam2";

    public List<Device> loadFromFile(String filePath) {
        List<Device> devices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            String line;
            int lineNum = 2;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    devices.add(Device.fromCsvLine(data));
                } catch (MalformedDataException e) {
                    System.err.println("Skipping line " + lineNum + " (malformed data): " + e.getMessage());
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return devices;
    }

    public void saveToFile(String filePath, List<Device> devices) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(CSV_HEADER);
            for (Device device : devices) {
                writer.println(device.toCsvLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving devices to file: " + e.getMessage());
        }
    }
}
