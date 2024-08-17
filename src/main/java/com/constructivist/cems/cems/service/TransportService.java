package com.constructivist.cems.cems.service;

import com.constructivist.cems.cems.model.Transport;
import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.model.UserId;
import com.constructivist.cems.cems.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    // CRUD operations
    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport getTransportById(Long transportId) {
        return transportRepository.findById(transportId).orElse(null);
    }

    public List<Transport> getAllVehicles() {
        return transportRepository.findAll();
    }

    public void deleteTransport(Long transportId) {
        transportRepository.deleteById(transportId);
    }

    // Leaderboard and pollution/energy calculations
    public double getTotalPollutionReductionByUser(User userId) {
        return transportRepository.findByUser(userId)
                .stream()
                .filter(Transport::isElectric).filter(Transport::isHavingV2G)
                .mapToDouble(Transport::getPollutionLevelReduction)
                .sum();
    }

    public double getTotalEnergyRegeneratedByUser(User userId) {
        return transportRepository.findByUser(userId)
                .stream()
                .filter(Transport::isElectric).filter(Transport::isHavingV2G)
                .mapToDouble(Transport::getRegeneratedEnergy)
                .sum();
    }

    public List<Transport> getTransportLeaderboard() {
        return getAllVehicles().stream()
                .sorted(Comparator.comparing(Transport::getRegeneratedEnergy).reversed())
                .collect(Collectors.toList());
    }


    public double getTotalPollutionByUser(User userId) {
        // Fetch all vehicles associated with the user
        List<Transport> vehicles = transportRepository.findByUser(userId);

        // Sum up the pollution levels of all vehicles
        return vehicles.stream()
                .mapToDouble(vehicle -> calculatePollution(vehicle))
                .sum();
    }

    // Helper method to calculate pollution based on vehicle properties
    private double calculatePollution(Transport vehicle) {
        // If the vehicle is electric, assume zero pollution
        if (vehicle.isElectric()) {
            return 0.0;
        }

        // Otherwise, calculate pollution based on fuel type and consumption
        switch (vehicle.getFuelType()) {
            case "Petrol":
                return vehicle.getFuelConsumption() * 2.31; // Example: 2.31 kg CO2 per liter
            case "Diesel":
                return vehicle.getFuelConsumption() * 2.68; // Example: 2.68 kg CO2 per liter
            case "CNG":
                return vehicle.getFuelConsumption() * 1.88; // Example: 1.88 kg CO2 per liter
            default:
                return vehicle.getPollutionLevel(); // If pollution is pre-calculated, use this field
        }
    }

    public List<Transport> getVehiclesByUser(User users) {
        return transportRepository.getVehiclesByUser(users);
    }
}
