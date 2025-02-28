package com.example.ogs.mapper;

import com.example.ogs.dto.SampleDTO;
import com.example.ogs.entity.Location;
import com.example.ogs.entity.Sample;
import org.springframework.stereotype.Component;

    @Component
    public class SampleMapper {

        public SampleDTO toDTO(Sample sample) {
            SampleDTO dto = new SampleDTO();
            dto.setSampleId(sample.getId());
            dto.setLocation(sample.getLocation().getName()); // Convert Location object to string
            dto.setDate(sample.getDate());
            dto.setUnitWeight(sample.getUnitWeight());
            dto.setWaterContent(sample.getWaterContent());
            dto.setShearStrength(sample.getShearStrength());
            return dto;
        }

        public Sample toEntity(SampleDTO dto, Location location) {
            Sample sample = new Sample();
            //sample.setId(dto.getSampleId());
            sample.setLocation(location); // Convert string to Location entity
            sample.setDate(dto.getDate());
            sample.setUnitWeight(dto.getUnitWeight());
            sample.setWaterContent(dto.getWaterContent());
            sample.setShearStrength(dto.getShearStrength());
            return sample;
        }
    }

