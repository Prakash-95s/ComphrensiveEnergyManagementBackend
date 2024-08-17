package com.constructivist.cems.cems.controller;

import org.springframework.web.bind.annotation.RequestBody;

import com.constructivist.cems.cems.model.HomeEnergy;
import com.constructivist.cems.cems.service.HomeEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/energy")
public class HomeEnergyController {

    @Autowired
    private HomeEnergyService energyService;

    @GetMapping("/{energyTypes}")
    public List<HomeEnergy> getAllHomeEnergyByTypes(@PathVariable String energyTypes) {
        return energyService.getAllHomeEnergyByTypes(energyTypes);
    }

    @GetMapping("/{id}")
    public HomeEnergy getEnergyById(@PathVariable Long id) {
        return energyService.getHomeById(id);
    }

    @PostMapping
    public HomeEnergy createEnergy(@RequestBody HomeEnergy energy) {
        return energyService.saveHomeEnergy(energy);
    }

    @PutMapping("/{id}")
    public HomeEnergy updateEnergy(@PathVariable Long id, @RequestBody HomeEnergy energy) {
        energy.setHomeEnergyId(id);
        return energyService.saveHomeEnergy(energy);
    }

    @DeleteMapping("/{id}")
    public void deleteEnergy(@PathVariable Long id) {
        energyService.deleteHome(id);
    }
}
