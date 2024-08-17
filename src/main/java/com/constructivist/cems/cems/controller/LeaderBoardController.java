package com.constructivist.cems.cems.controller;

import com.constructivist.cems.cems.model.LeaderBoard;
import com.constructivist.cems.cems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {

    @Autowired
    private UserService userService;

    @Autowired
    private HomeEnergyService homeEnergyService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private GridEnergyService gridEnergyService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public LeaderBoard getLeaderBoard() {
        LeaderBoard leaderboard = new LeaderBoard();

        // Populate leaderboard using the services
        leaderboard.calculateLeaderboard(
                userService,
                homeEnergyService,
                transportService,
                gridEnergyService,
                cityService
        );

        return leaderboard;
    }
}
