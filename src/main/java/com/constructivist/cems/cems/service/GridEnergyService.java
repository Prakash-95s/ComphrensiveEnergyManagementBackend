package com.constructivist.cems.cems.service;

import com.constructivist.cems.cems.model.GridEnergy;
import com.constructivist.cems.cems.model.HomeEnergy;
import com.constructivist.cems.cems.repository.GridEnergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridEnergyService {

    @Autowired
    private GridEnergyRepository energyRepository;

    public List<GridEnergy> getAllGrids() {
        return energyRepository.findAll();
    }

    public GridEnergy getGridById(Long id) {
        return energyRepository.findById(id).orElse(null);
    }

    public GridEnergy saveGrid(GridEnergy gridEnergy) {
        return energyRepository.save(gridEnergy);
    }

    public void deleteGrid(Long id) {
        energyRepository.deleteById(id);
    }

    // Calculate the total energy saved by the grid
    public double calculateTotalEnergySaved(Long gridId) {
        GridEnergy grid = getGridById(gridId);
        return grid != null ? grid.getEnergySaved() : 0;
    }
    public double getTotalEnergySavedByGrid(Long gridId) {
        return energyRepository.findById(gridId)
                .map(gridEnergy -> gridEnergy.getHomes().stream()
                        .mapToDouble(HomeEnergy::getEnergySaved)
                        .sum())
                .orElse(0.0);
    }

    // Calculate the leaderboard for the best-performing grids
    public List<GridEnergy> getGridLeaderboard() {
        List<GridEnergy> grids = getAllGrids();
        grids.sort((g1, g2) -> Double.compare(g2.getEnergySaved(), g1.getEnergySaved())); // Sort by energy saved
        return grids;
    }
}
