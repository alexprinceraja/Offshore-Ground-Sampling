package com.example.ogs.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SampleDTO {
    private Long sampleId;
    @Schema(description = "Location name as a string", example = "Amsterdam")
    private String location;
    @Schema(description = "Date when the sample was collected", example = "2024-03-01")
    private String date;
    @Schema(description = "Unit weight of the sample in kN/m³", example = "18.0")
    private double unitWeight;
    @Schema(description = "Water content percentage of the sample", example = "25.0")
    private double waterContent;
    @Schema(description = "Shear strength of the sample in kPa", example = "200")
    private double shearStrength;

    public SampleDTO() {
    }

    public SampleDTO(String location, String date, double unitWeight, double waterContent, double shearStrength) {
        this.sampleId = sampleId;
        this.location = location;
        this.date = date;
        this.unitWeight = unitWeight;
        this.waterContent = waterContent;
        this.shearStrength = shearStrength;
    }

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(double unitWeight) {
        this.unitWeight = unitWeight;
    }

    public double getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(double waterContent) {
        this.waterContent = waterContent;
    }

    public double getShearStrength() {
        return shearStrength;
    }

    public void setShearStrength(double shearStrength) {
        this.shearStrength = shearStrength;
    }
}
