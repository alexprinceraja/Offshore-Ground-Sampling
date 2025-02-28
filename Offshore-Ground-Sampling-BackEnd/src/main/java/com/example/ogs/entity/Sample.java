package com.example.ogs.entity;

import jakarta.persistence.*;

@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private String date;
    private double unitWeight;
    private double waterContent;
    private double shearStrength;

    public Sample() {
    }

    public Sample(Long id, Location location, String date, double unitWeight, double waterContent, double shearStrength) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.unitWeight = unitWeight;
        this.waterContent = waterContent;
        this.shearStrength = shearStrength;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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