package com.constructivist.cems.cems.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class HomeEnergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_id",nullable = false,insertable=false, updatable=false)
    private Long homeEnergyId;
    @Column(name = "homeName",nullable = false)
    private String homeName;
    private String energyTypes;
    private double energyConsumption;
    private String appliancesTypes;
    private double carbonFootprint;
    private boolean isApartment; //// Boolean to indicate if the home is an apartment
    private double energySaved; //Energy saved calculated from service logic
    @OneToMany(mappedBy = "homeEnergy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;// A home can have multiple users (e.g., an apartment)}
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="home_id",insertable = false, updatable = false)
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_id",insertable = false, updatable = false)
    private GridEnergy gridEnergy;
    @JsonCreator
    public HomeEnergy(@JsonProperty("homeEnergyId") Long homeEnergyId,
                      @JsonProperty("homeName") String homeName,
                      @JsonProperty("energyTypes") String energyTypes,
                      @JsonProperty("energyConsumption") Double energyConsumption,
                      @JsonProperty("appliancesTypes") String appliancesTypes,
                      @JsonProperty("carbonFootprint") double carbonFootprint,
                      @JsonProperty("energySaved") double energySaved,
                      @JsonProperty("users") List<User> users,
                      @JsonProperty("city") City city,
                      @JsonProperty("gridEnergy") GridEnergy gridEnergy,
                      @JsonProperty("apartment") boolean apartment) {
        this.homeEnergyId = homeEnergyId;
        this.homeName = homeName;
        this.energyTypes = energyTypes;
        this.energyConsumption = energyConsumption;
        this.appliancesTypes = appliancesTypes;
        this.carbonFootprint = carbonFootprint;
        this.energySaved = energySaved;
        this.users = users;
        this.city = city;
        this.gridEnergy = gridEnergy;
        this.isApartment = apartment;
    }

}


    // Constructors, getters, setters, etc.
