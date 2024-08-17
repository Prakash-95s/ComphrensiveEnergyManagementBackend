package com.constructivist.cems.cems.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class GridEnergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gridEnergyId;

    private String gridType; // e.g., SmartGrid or ConventionalGrid
    private boolean isSmartGrid;

    private double totalEnergyProduced; // Total energy produced in kW
    private double energyConsumed; // Total energy consumed by homes
    private double energySaved; // Total energy saved through conservation efforts
    private double energyRegenerated; // Total energy regenerated back to the grid (e.g., via EVs)

    private int houseSupplyCapacity; // Number of houses this grid can supply

    @OneToMany(mappedBy = "gridEnergy", cascade = CascadeType.ALL)
    private List<HomeEnergy> homes; // Associated homes under this grid

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city; // Associated city for this grid
}
