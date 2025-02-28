package com.example.ogs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.test.context.TestConfiguration;
import org.mockito.Mockito;
import com.example.ogs.service.LocationService;

@TestConfiguration
public class TestConfig {

    @Bean
    public LocationService locationService() {
        return Mockito.mock(LocationService.class);
    }
}