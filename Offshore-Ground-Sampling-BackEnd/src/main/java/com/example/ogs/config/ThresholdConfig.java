package com.example.ogs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ThresholdConfig {

    @Value("${threshold.waterContent.min}")
    private double minWaterContent;

    @Value("${threshold.waterContent.max}")
    private double maxWaterContent;

    @Value("${threshold.unitWeight.min}")
    private double minUnitWeight;

    @Value("${threshold.unitWeight.max}")
    private double maxUnitWeight;

    @Value("${threshold.shearStrength.min}")
    private double minShearStrength;

    @Value("${threshold.shearStrength.max}")
    private double maxShearStrength;

    public double getMinWaterContent() { return minWaterContent; }
    public double getMaxWaterContent() { return maxWaterContent; }
    public double getMinUnitWeight() { return minUnitWeight; }
    public double getMaxUnitWeight() { return maxUnitWeight; }
    public double getMinShearStrength() { return minShearStrength; }
    public double getMaxShearStrength() { return maxShearStrength; }
}

