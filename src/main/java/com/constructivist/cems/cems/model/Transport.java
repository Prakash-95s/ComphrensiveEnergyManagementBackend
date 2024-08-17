package com.constructivist.cems.cems.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

 @JsonCreator
 public Transport(@JsonProperty("vehicleId") Long vehicleId,
                  @JsonProperty("vehicleType") String vehicleType,
                  @JsonProperty("energyModelType") String energyModelType,
                  @JsonProperty("fuelType") String fuelType,
                  @JsonProperty("fuelConsumption") double fuelConsumption,
                  @JsonProperty("carbonFootprintData") String carbonFootprintData,
                  @JsonProperty("vehicleToGridPowerRegeneration") Long vehicleToGridPowerRegeneration,
                  @JsonProperty("regeneratedEnergy") int regeneratedEnergy,
                  @JsonProperty("havingV2G") boolean havingV2G,
                  @JsonProperty("pollutionLevel") double pollutionLevel,
                  @JsonProperty("pollutionLevelReduction") int pollutionLevelReduction,
                  @JsonProperty("user") User user,
                  @JsonProperty("city") City city,
                  @JsonProperty("electric") boolean electric,
                  @JsonProperty("publicTransport") boolean publicTransport) {
  this.vehicleId = vehicleId;
  this.vehicleType = vehicleType;
  this.energyModelType = energyModelType;
  this.fuelType = fuelType;
  this.fuelConsumption = fuelConsumption;
  this.carbonFootprintData = carbonFootprintData;
  this.vehicleToGridPowerRegeneration = vehicleToGridPowerRegeneration;
  this.regeneratedEnergy = regeneratedEnergy;
  this.havingV2G = havingV2G;
  this.pollutionLevel = pollutionLevel;
  this.pollutionLevelReduction = pollutionLevelReduction;
  this.user = user;
  this.city = city;
  this.isElectric = electric;
  this.isPublicTransport = publicTransport;
 }


 // Getters and Setters
}
