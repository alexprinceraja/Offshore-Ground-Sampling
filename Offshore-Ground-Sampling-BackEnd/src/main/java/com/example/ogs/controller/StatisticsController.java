package com.example.ogs.controller;

import com.example.ogs.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@Tag(name = "Statistics API", description = "Get Statistics Information")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    @Operation(summary = "Get Statistics", description = "Retrieves a list of Statistics")
    public Map<String, Object> getStatistics() {
        return statisticsService.getStatistics();
    }
}
