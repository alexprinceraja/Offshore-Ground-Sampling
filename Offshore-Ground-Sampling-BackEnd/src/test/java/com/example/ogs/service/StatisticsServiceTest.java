package com.example.ogs.service;

import com.example.ogs.config.ThresholdConfig;
import com.example.ogs.entity.Sample;
import com.example.ogs.repository.SampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest {

    private StatisticsService statisticsService;

    @Mock
    private SampleRepository sampleRepository;

    @Mock
    private ThresholdConfig thresholdConfig;

    private Sample sample;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statisticsService = new StatisticsService(sampleRepository, thresholdConfig);

        sample = new Sample();
        sample.setId(1L);
        sample.setWaterContent(18.0);
        sample.setUnitWeight(15.0);
        sample.setShearStrength(250.0);
    }

    @Test
    public void testCalculateAverageWaterContent() {
        List<Sample> samples = Arrays.asList(sample);
        when(sampleRepository.findAll()).thenReturn(samples);

        double result = statisticsService.calculateAverageWaterContent();

        assertEquals(18.0, result);
    }

    @Test
    public void testFindSamplesExceedingThresholds() {
        List<Sample> samples = Arrays.asList(sample);
        when(sampleRepository.findAll()).thenReturn(samples);
        when(thresholdConfig.getMaxWaterContent()).thenReturn(17.0);
        when(thresholdConfig.getMaxUnitWeight()).thenReturn(14.0);
        when(thresholdConfig.getMaxShearStrength()).thenReturn(240.0);

        List<Sample> result = statisticsService.findSamplesExceedingThresholds();

        assertEquals(samples, result);
    }

    @Test
    public void testGetStatistics() {
        List<Sample> samples = Arrays.asList(sample);
        when(sampleRepository.findAll()).thenReturn(samples);
        when(thresholdConfig.getMaxWaterContent()).thenReturn(17.0);
        when(thresholdConfig.getMaxUnitWeight()).thenReturn(14.0);
        when(thresholdConfig.getMaxShearStrength()).thenReturn(240.0);

        Map<String, Object> result = statisticsService.getStatistics();

        assertEquals(18.0, result.get("averageWaterContent"));
        assertEquals(samples, result.get("exceedingSamples"));
    }
}