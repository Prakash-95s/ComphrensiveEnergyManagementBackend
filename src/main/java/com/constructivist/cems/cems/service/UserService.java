package com.constructivist.cems.cems.service;

import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.model.UserId;
import com.constructivist.cems.cems.repository.UserRepository;
import com.constructivist.cems.cems.service.HomeEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeEnergyService homeEnergyService;
    @Autowired
    private TransportService transportService;

    // CRUD operations
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(UserId userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UserId userId) {
        userRepository.deleteById(userId);
    }

    // Leaderboard and score calculations
    public double calculateUserScore(User user) {
        double energySaved = homeEnergyService.getTotalEnergySavedByUser(user);
        double pollutionReduced = transportService.getTotalPollutionReductionByUser(user);
        return energySaved * 1.5 - pollutionReduced * 0.5;
    }

    public void updateUserScores() {
        List<User> users = getAllUsers();
        for (User user : users) {
            double score = calculateUserScore(user);
            user.setScore(score);
            saveUser(user);
        }
    }

    public List<User> getUserLeaderboard() {
        updateUserScores();
        return getAllUsers().stream()
                .sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toList());
    }
}
