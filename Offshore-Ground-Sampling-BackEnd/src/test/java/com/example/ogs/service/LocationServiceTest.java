package com.example.ogs.service;

import com.example.ogs.entity.Location;
import com.example.ogs.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LocationServiceTest {

    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    private Location location;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locationService = new LocationService(locationRepository);

        location = new Location();
        location.setId(1L);
        location.setName("Amsterdam");
    }

    @Test
    public void testGetAllLocations() {
        List<Location> locations = Arrays.asList(location);
        when(locationRepository.findAll()).thenReturn(locations);

        List<Location> result = locationService.getAllLocations();

        assertEquals(locations, result);
    }

    @Test
    public void testGetAllLocationNames() {
        List<String> locationNames = Arrays.asList("Amsterdam");
        when(locationRepository.findAll()).thenReturn(Arrays.asList(location));

        List<String> result = locationService.getAllLocationNames();

        assertEquals(locationNames, result);
    }

    @Test
    public void testLocationFindByName() {
        when(locationRepository.findByName("Amsterdam")).thenReturn(location);

        Location result = locationService.locationFindByName("Amsterdam");

        assertEquals(location, result);
    }
}