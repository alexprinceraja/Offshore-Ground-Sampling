package com.example.ogs.service;

import com.example.ogs.config.ThresholdConfig;
import com.example.ogs.entity.Sample;
import com.example.ogs.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ThresholdConfig thresholdConfig;


    public double calculateAverageWaterContent() {
        List<Sample> samples = sampleRepository.findAll();
        OptionalDouble avg = samples.stream().mapToDouble(Sample::getWaterContent).average();
        return avg.orElse(0.0);
    }

    public List<Sample> findSamplesExceedingThresholds() {
        return sampleRepository.findAll().stream()
                .filter(sample -> sample.getWaterContent() > thresholdConfig.getMaxWaterContent() ||
                        sample.getUnitWeight() > thresholdConfig.getMaxUnitWeight() ||
                        sample.getShearStrength() > thresholdConfig.getMaxShearStrength())
                .collect(Collectors.toList());
    }

    public Map<String, Object> getStatistics() {
        return Map.of(
                "averageWaterContent", calculateAverageWaterContent(),
                "exceedingSamples", findSamplesExceedingThresholds()
        );
    }
}
