package com.constructivist.cems.cems.service;

import com.constructivist.cems.cems.model.City;
import com.constructivist.cems.cems.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // CRUD operations
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public boolean deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
        return false;
    }

    // Leaderboard and contribution calculations
    public double calculateCityContribution(Long cityId) {
        getCityById(cityId);
        return getCityById(cityId) != null ? City.getOverallContribution(getCityById(cityId)) : 0.0;
    }

    public List<City> getCityLeaderboard(City cities) {
        return getAllCities().stream()
                .sorted(Comparator.comparing(t -> City.getOverallContribution(cities)).reversed())
                .collect(Collectors.toList());
    }

    public City createCity(City city) {
        cityRepository.save(city);
        return city;
    }

    public Optional<City> updateCity(Long id, City cityDetails) {
            return cityRepository.findById(id).map(city -> {
                city.setName(cityDetails.getName());
                city.setState(cityDetails.getState());
                city.setCountry(cityDetails.getCountry());
                city.setPopulation(cityDetails.getPopulation());
                return cityRepository.save(city);
            });
        }


}


