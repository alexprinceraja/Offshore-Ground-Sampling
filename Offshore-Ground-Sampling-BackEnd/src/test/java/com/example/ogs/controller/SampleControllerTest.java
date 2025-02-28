package com.example.ogs.controller;

import com.example.ogs.dto.SampleDTO;
import com.example.ogs.service.LocationService;
import com.example.ogs.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SampleService sampleService;
    @Mock
    private LocationService locationService;

    private SampleDTO sampleDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleDTO = new SampleDTO();
        sampleDTO.setSampleId(1L);
        sampleDTO.setLocation("Rotterdam");
        sampleDTO.setDate("2024-01-02");
        sampleDTO.setUnitWeight(16.0);
        sampleDTO.setWaterContent(17.0);
        sampleDTO.setShearStrength(260.0);
    }

    @Test
    public void testCreateSample() throws Exception {
        when(sampleService.createSample(sampleDTO)).thenReturn(sampleDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sampleId\": 1, \"location\": \"Rotterdam\", \"date\": \"2024-01-02\", \"unitWeight\": 16.0, \"waterContent\": 17.0, \"shearStrength\": 260.0}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'sampleId': 1, 'location': 'Rotterdam', 'date': '2024-01-02', 'unitWeight': 16.0, 'waterContent': 17.0, 'shearStrength': 260.0}"));
    }

    @Test
    public void testGetAllSamples() throws Exception {
        List<SampleDTO> sampleDTOs = Arrays.asList(sampleDTO);
        when(sampleService.getAllSamples()).thenReturn(sampleDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/samples")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'sampleId': 1, 'location': 'Rotterdam', 'date': '2024-01-02', 'unitWeight': 16.0, 'waterContent': 17.0, 'shearStrength': 260.0}]"));
    }

    @Test
    public void testUpdateSample() throws Exception {
        when(sampleService.updateSample(sampleDTO)).thenReturn(sampleDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/samples/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sampleId\": 1, \"location\": \"Rotterdam\", \"date\": \"2024-01-02\", \"unitWeight\": 16.0, \"waterContent\": 17.0, \"shearStrength\": 260.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'sampleId': 1, 'location': 'Rotterdam', 'date': '2024-01-02', 'unitWeight': 16.0, 'waterContent': 17.0, 'shearStrength': 260.0}"));
    }

    @Test
    public void testDeleteSample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/samples/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidDataInputs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/samples")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sampleId\": 2, \"location\": \"Invalid Location\", \"date\": \"2024-01-02\", \"unitWeight\": 10.0, \"waterContent\": 160.0, \"shearStrength\": 1500.0}"))
                .andExpect(status().isBadRequest());
    }
}