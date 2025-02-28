package com.example.ogs.service;

import com.example.ogs.dto.SampleDTO;
import com.example.ogs.entity.Location;
import com.example.ogs.entity.Sample;
import com.example.ogs.mapper.SampleMapper;
import com.example.ogs.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleService {
    @Autowired
    private SampleRepository repository;
    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private LocationService locationService;

    public List<SampleDTO> getAllSamples() {
        return repository.findAll().stream()
                .map(sampleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SampleDTO createSample(SampleDTO sampleDTO) {
        Location location = locationService.locationFindByName(sampleDTO.getLocation());
        Sample sampleEntity = sampleMapper.toEntity(sampleDTO, location);
        return sampleMapper.toDTO(repository.save(sampleEntity));
    }

    public SampleDTO updateSample(SampleDTO sample) {
        Location location = locationService.locationFindByName(sample.getLocation());
        Sample sampleEntity = sampleMapper.toEntity(sample, location);
        sampleEntity.setId(sample.getSampleId());
        return sampleMapper.toDTO(repository.save(sampleEntity));
    }

    public void deleteSample(Long id) {
        repository.deleteById(id);
    }
}
