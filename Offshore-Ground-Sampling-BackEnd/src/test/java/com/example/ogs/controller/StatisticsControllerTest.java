package com.example.ogs.controller;

import com.example.ogs.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StatisticsService statisticsService;

    private Map<String, Object> statistics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statistics = new HashMap<>();
        statistics.put("totalSamples", 100);
        statistics.put("averageWeight", 20.5);
    }

    @Test
    public void testGetStatistics() throws Exception {
        when(statisticsService.getStatistics()).thenReturn(statistics);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"totalSamples\": 100, \"averageWeight\": 20.5}"));
    }
}