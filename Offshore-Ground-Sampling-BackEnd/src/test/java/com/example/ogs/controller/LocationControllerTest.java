package com.example.ogs.controller;

import com.example.ogs.config.TestConfig;
import com.example.ogs.entity.Location;
import com.example.ogs.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
@Import(TestConfig.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LocationService locationService;

    private Location location;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location();
        location.setId(1L);
        location.setName("Amsterdam");
    }

    @Test
    public void testGetAllLocations() throws Exception {
        List<Location> locations = Arrays.asList(location);
        when(locationService.getAllLocations()).thenReturn(locations);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/locations")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Amsterdam'}]"));
    }

    @Test
    public void testGetAllLocationNames() throws Exception {
        List<String> locationNames = Arrays.asList("Amsterdam");
        when(locationService.getAllLocationNames()).thenReturn(locationNames);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/locations/names")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("['Amsterdam']"));
    }
}