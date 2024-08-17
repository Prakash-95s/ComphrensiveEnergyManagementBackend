package com.constructivist.cems.cems.model;
import com.constructivist.cems.cems.service.*;
import lombok.Data;

import java.util.List;


@Data
public class LeaderBoard {

    private List<User> topUsers;
    private List<HomeEnergy> topHomes;
    private List<Transport> topTransports;
    private List<GridEnergy> topGrids;
    private List<City> topCities;

    public void calculateLeaderboard(
            UserService userService,
            HomeEnergyService homeEnergyService,
            TransportService transportService,
            GridEnergyService gridEnergyService,
            CityService cityService) {

        this.topUsers = userService.getUserLeaderboard().subList(0, Math.min(10, userService.getUserLeaderboard().size()));
        this.topHomes = homeEnergyService.getHomeLeaderboard().subList(0, Math.min(10, homeEnergyService.getHomeLeaderboard().size()));
        this.topTransports = transportService.getTransportLeaderboard().subList(0, Math.min(10, transportService.getTransportLeaderboard().size()));
        this.topGrids = gridEnergyService.getGridLeaderboard().subList(0, Math.min(10, gridEnergyService.getGridLeaderboard().size()));
        this.topCities = cityService.getCityLeaderboard(topCities.stream().iterator().next()).subList(0, Math.min(10, cityService.getCityLeaderboard(topCities.stream().iterator().next()).size()));
    }
}
