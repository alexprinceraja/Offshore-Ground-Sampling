package com.example.ogs.controller;

import com.example.ogs.entity.Location;
import com.example.ogs.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Location API", description = "Get Location Information")
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping
    @Operation(summary = "Get all Locations", description = "Retrieves a list of Locations")
    public List<Location> getAllLocations() {
        return service.getAllLocations();
    }

    @GetMapping("/names")
    @Operation(summary = "Get all Location Name", description = "Retrieves a list of all Locations Name")
    public List<String> getAllLocationNames() {
        return service.getAllLocationNames();
    }
}
