package com.constructivist.cems.cems.service;

import com.constructivist.cems.cems.model.HomeEnergy;
import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.model.UserId;
import com.constructivist.cems.cems.repository.HomeEnergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeEnergyService {

    @Autowired
    private HomeEnergyRepository homeEnergyRepository;

    // CRUD operations
    public HomeEnergy saveHomeEnergy(HomeEnergy homeEnergy) {
        return homeEnergyRepository.save(homeEnergy);
    }

    public HomeEnergy getHomeById(Long homeId) {
        return homeEnergyRepository.findById(homeId).orElse(null);
    }

    public List<HomeEnergy> getAllHomes() {
        return homeEnergyRepository.findAll();
    }

    public void deleteHome(Long homeId) {
        homeEnergyRepository.deleteById(homeId);
    }

    // Leaderboard and energy calculations
    // Get total energy saved by a user
    public double getTotalEnergySavedByUser(User users) {
        return homeEnergyRepository.findByUsers(users)
                .stream()
                .mapToDouble(homeEnergy -> homeEnergy.getEnergySaved())
                .sum();
    }

    // Get total energy saved by a specific home
    public double getTotalEnergySavedByHome(Long homeId) {
        HomeEnergy homeEnergy = getHomeById(homeId);
        return homeEnergy != null ? homeEnergy.getEnergySaved() : 0.0;
    }

    // Get the home energy leaderboard
    public List<HomeEnergy> getHomeLeaderboard() {
        return getAllHomes().stream()
                .sorted(Comparator.comparingDouble(HomeEnergy::getEnergySaved).reversed())
                .collect(Collectors.toList());
    }

    public List<HomeEnergy> getAllHomeEnergyByTypes(String energyTypes) {
        return homeEnergyRepository.findByEnergyTypes(energyTypes);
    }
    }