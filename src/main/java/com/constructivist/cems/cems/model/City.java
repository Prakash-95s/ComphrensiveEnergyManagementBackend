package com.constructivist.cems.cems.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city-id",nullable = false)
    private Long cityId;

    private String name;
    private String state;
    private String population;
    private String country; // Country where the city is located

    // A Json constructor
    @JsonCreator
    public City(@JsonProperty("cityId") Long cityId,
                @JsonProperty("name") String name,
                @JsonProperty("state") String state,
                @JsonProperty("population") String population,
                @JsonProperty("country") String country,
                @JsonProperty("grids") List<GridEnergy> grids,
                @JsonProperty("users") List<User> users,
                @JsonProperty("homes") List<HomeEnergy> homes,
                @JsonProperty("transports") List<Transport> transports) {
        this.cityId = cityId;
        this.name = name;
        this.state = state;
        this.population = population;
        this.country = country;
        this.grids = grids;
        this.users = users;
        this.homes = homes;
        this.transports = transports;
    }
    // y can have multiple grids
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GridEnergy> grids;

    // A city can have multiple users
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    // A city can have multiple homes
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HomeEnergy> homes;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Transport>transports;

    public static <U extends Comparable<? super U>, T> U getOverallContribution(T t) {
        // Initialize the contribution value (e.g., Double)
        double contribution = 0.0;

        // Check the type of T (can be User, City, or GridEnergy)
        if (t instanceof User) {
            User user = (User) t;
            // Contribution logic for User
            contribution = calculateUserContribution(user);
        } else if (t instanceof City) {
            City city = (City) t;
            // Contribution logic for City
            contribution = calculateCityContribution(city);
        } else if (t instanceof GridEnergy) {
            GridEnergy grid = (GridEnergy) t;
            // Contribution logic for GridEnergy
            contribution = calculateGridContribution(grid);
        }

        // Return the contribution as the type U
        return (U) Double.valueOf(contribution);
    }

    // Method to calculate contribution for a User
    private static double calculateUserContribution(User user) {
        double energySaved = user.getHomeEnergy().getEnergySaved();
        double pollutionReduced = 0.0;
        for (Transport transport : user.getTransports()) {
            pollutionReduced =pollutionReduced+transport.getPollutionLevelReduction();
            // Example logic: energy saved contributes positively, pollution reduced contributes negatively

        }
        return energySaved * 1.5 - pollutionReduced * 0.5;
    }

    // Method to calculate contribution for a City
    private static double calculateCityContribution(City city) {
        double totalEnergySaved = 0.0;
        double totalPollutionReduced = 0.0;
        for (Transport transport : city.getTransports()) {
             totalEnergySaved = city.getGrids().stream()
                    .mapToDouble(GridEnergy::getEnergySaved)
                    .sum();
            totalPollutionReduced = city.getUsers().stream()
                    .mapToDouble(user -> transport.getPollutionLevelReduction())
                    .sum();
            // Example logic: Sum of energy saved and pollution reduced for the city

            }
        return totalEnergySaved * 1.2 - totalPollutionReduced * 0.8;
        }

    // Method to calculate contribution for a GridEnergy
    private static double calculateGridContribution(GridEnergy grid) {
        double energyProduced = grid.getTotalEnergyProduced();
        double energyRegenerated = grid.getEnergyRegenerated();
        // Example logic: contribution based on energy produced and regenerated
        return energyProduced + energyRegenerated * 0.8;
        }

    // Constructor, Getters, and Setters

    // Other business logic methods (e.g., calculate overall contribution)
}

