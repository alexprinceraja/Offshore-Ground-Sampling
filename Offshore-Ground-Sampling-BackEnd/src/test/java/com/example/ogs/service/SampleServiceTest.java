package com.example.ogs.service;

import com.example.ogs.dto.SampleDTO;
import com.example.ogs.entity.Location;
import com.example.ogs.entity.Sample;
import com.example.ogs.mapper.SampleMapper;
import com.example.ogs.repository.SampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SampleServiceTest {

    private SampleService sampleService;

    @Mock
    private SampleRepository sampleRepository;

    @Mock
    private SampleMapper sampleMapper;

    @Mock
    private LocationService locationService;

    private SampleDTO sampleDTO;
    private Sample sample;
    private Location location;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleService = new SampleService(sampleRepository, sampleMapper, locationService);

        location = new Location();
        location.setId(1L);
        location.setName("Rotterdam");

        sampleDTO = new SampleDTO();
        sampleDTO.setSampleId(1L);
        sampleDTO.setLocation("Rotterdam");
        sampleDTO.setDate("2024-01-02");
        sampleDTO.setUnitWeight(16.0);
        sampleDTO.setWaterContent(17.0);
        sampleDTO.setShearStrength(260.0);

        sample = new Sample();
        sample.setId(1L);
        sample.setLocation(location);
        sample.setDate("2024-01-02");
        sample.setUnitWeight(16.0);
        sample.setWaterContent(17.0);
        sample.setShearStrength(260.0);
    }

    @Test
    public void testGetAllSamples() {
        List<Sample> samples = Arrays.asList(sample);
        List<SampleDTO> sampleDTOs = Arrays.asList(sampleDTO);

        when(sampleRepository.findAll()).thenReturn(samples);
        when(sampleMapper.toDTO(sample)).thenReturn(sampleDTO);

        List<SampleDTO> result = sampleService.getAllSamples();

        assertEquals(sampleDTOs, result);
    }

    @Test
    public void testCreateSample() {
        when(locationService.locationFindByName("Rotterdam")).thenReturn(location);
        when(sampleMapper.toEntity(sampleDTO, location)).thenReturn(sample);
        when(sampleRepository.save(sample)).thenReturn(sample);
        when(sampleMapper.toDTO(sample)).thenReturn(sampleDTO);

        SampleDTO result = sampleService.createSample(sampleDTO);

        assertEquals(sampleDTO, result);
    }

    @Test
    public void testUpdateSample() {
        when(locationService.locationFindByName("Rotterdam")).thenReturn(location);
        when(sampleMapper.toEntity(sampleDTO, location)).thenReturn(sample);
        when(sampleRepository.save(sample)).thenReturn(sample);
        when(sampleMapper.toDTO(sample)).thenReturn(sampleDTO);

        SampleDTO result = sampleService.updateSample(sampleDTO);

        assertEquals(sampleDTO, result);
    }

    @Test
    public void testDeleteSample() {
        doNothing().when(sampleRepository).deleteById(1L);

        sampleService.deleteSample(1L);

        verify(sampleRepository, times(1)).deleteById(1L);
    }
}