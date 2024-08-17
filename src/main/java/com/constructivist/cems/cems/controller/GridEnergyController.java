package com.constructivist.cems.cems.controller;

import com.constructivist.cems.cems.model.GridEnergy;
import com.constructivist.cems.cems.service.GridEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/grids")
public class GridEnergyController {

    @Autowired
    private GridEnergyService gridEnergyService;

    // Get all grids
    @GetMapping
    public List<GridEnergy> getAllGrids() {
        return gridEnergyService.getAllGrids();
    }

    // Get grid by ID
    @GetMapping("/{id}")
    public ResponseEntity<GridEnergy> getGridById(@PathVariable("id") Long id) {
        GridEnergy gridEnergy = gridEnergyService.getGridById(id);
        return gridEnergy != null ? ResponseEntity.ok(gridEnergy) : ResponseEntity.notFound().build();
    }

    // Create or update grid data
    @PostMapping
    public ResponseEntity<GridEnergy> createGrid(@RequestBody GridEnergy gridEnergy) {
        GridEnergy savedGridEnergy = gridEnergyService.saveGrid(gridEnergy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGridEnergy);
    }

    // Delete grid by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrid(@PathVariable("id") Long id) {
        gridEnergyService.deleteGrid(id);
        return ResponseEntity.noContent().build();
    }

    // Get grid leaderboard
    @GetMapping("/leaderboard")
    public List<GridEnergy> getGridLeaderboard() {
        return gridEnergyService.getGridLeaderboard();
    }
}

