# Smart Home Energy Management System (SHEMS)

**SHEMS** is a Java-based backend simulation designed to monitor and manage household energy consumption. This project serves as a comprehensive preparation for Spring Boot development by emphasizing **OOP/SOLID** principles, **Java Streams**, **Lambda Expressions**, and **File I/O**.

## 🚀 Key Features

The project is structured into four developmental phases to mimic a real-world backend service:

1.  **Core Architecture (OOP & SOLID):** A flexible system designed to allow new device types to be added without breaking existing code.
    * **Device Models:** Includes an abstract base `Device` class and specific implementations like `SmartLight`, `AirConditioner`, and `SmartTV`.
2.  **Data Management (Collections & File I/O):** Persistence logic to ensure the "house" remembers devices even after the program closes.
    * **File Storage:** Devices are saved to and loaded from a `.csv` or `.txt` file.
    * **Exception Handling:** Robust management of `FileNotFoundException` and malformed data.
3.  **Analytics Engine (Streams & Lambdas):** High-performance data querying using modern Java features.
    * Total energy usage calculation using `stream().mapToDouble().sum()`.
    * Filtering high consumers and sorting devices by consumption.
    * Advanced grouping of device types using `Collectors.groupingBy()`.
4.  **Concurrency (Advanced Simulation):** Real-time simulation of human behavior.
    * A background thread randomly toggles devices ON/OFF every 10 seconds to simulate usage.

## 📁 Project Structure

The project follows a standard backend directory layout:

```text
src/main/java/com/shems/
├── model/        # Abstract Device and specific implementations (AC, TV, Light)
├── repository/   # File I/O logic for data persistence (simulating a database)
├── service/      # Business logic, EnergyService, Streams, and Lambdas
├── exception/    # Custom exceptions like DeviceNotFoundException
└── Main.java     # Application Entry Point