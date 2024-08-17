package com.constructivist.cems.cems.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transport {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "vehicle-id",nullable = false)
 private long vehicleId;
 private boolean isPublicTransport;

 private String vehicleType; // e.g., Car, Bike, Bus
 private String energyModelType; // e.g., Hybrid, Electric, Gasoline
 private String fuelType; // e.g., Petrol, Diesel, Electric
 private double fuelConsumption; // in liters or kWh

 private String carbonFootprintData; // e.g., CO2 emission data
 private Long vehicleToGridPowerRegeneration;
 private int regeneratedEnergy;// Energy regenerated through V2G (in kWh)
 private boolean isElectric;
 private boolean havingV2G;
 private double pollutionLevel;
 private double pollutionLevelReduction;
 // Relations to user entity
 @ManyToOne
 @JoinColumn(name = "user_id")
 @JoinColumn(name = "home_id")
 private User user; // Link to the user who owns the vehicle
 @ManyToOne
 @JoinColumn(name ="city_id")
 private City city;



 // Getters and Setters
}
