package com.example.ogs.service;

import com.example.ogs.entity.Location;
import com.example.ogs.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    public List<String> getAllLocationNames() {
        return repository.findAll().stream().map(Location::getName).collect(Collectors.toList());
    }

    public Location locationFindByName(String location) {
        return repository.findByName(location);
    }
}

