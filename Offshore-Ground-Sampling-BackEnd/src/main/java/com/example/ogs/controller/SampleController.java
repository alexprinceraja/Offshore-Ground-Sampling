package com.example.ogs.controller;

import com.example.ogs.dto.SampleDTO;
import com.example.ogs.service.LocationService;
import com.example.ogs.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
@Tag(name = "Sample API", description = "Manage Offshore Ground Sampling")
class SampleController {
    @Autowired
    private SampleService service;
    @Autowired
    private LocationService locationService;

    @GetMapping
    @Operation(summary = "Get all Sampling", description = "Retrieves a list of all Sampling")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of samples"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error while fetching samples")
//    })
    public List<SampleDTO> getAllSamples() {
        return service.getAllSamples();
    }

    @PostMapping
    @Operation(summary = "Add a new Sampling", description = "Creates a new Sampling entry")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "Sample successfully created"),
//            @ApiResponse(responseCode = "400", description = "Invalid request body"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error while saving sample")
//    })
    public ResponseEntity<SampleDTO> addSample(@RequestBody SampleDTO sampleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSample(sampleDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit a Sampling", description = "Update a Sampling entry  by ID")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Sample successfully updated"),
//            @ApiResponse(responseCode = "400", description = "Invalid request body or missing fields"),
//            @ApiResponse(responseCode = "404", description = "Sample not found"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error while updating sample")
//    })
    public SampleDTO updateSample(@PathVariable Long id, @RequestBody SampleDTO updatedSample) {
        updatedSample.setSampleId(id);
        return service.updateSample(updatedSample);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Sampling", description = "Remove a Sampling by ID")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Sampling successfully deleted"),
//            @ApiResponse(responseCode = "404", description = "Sampling not found"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error while deleting sample")
//    })
    public void deleteSample(@PathVariable Long id) {
        service.deleteSample(id);
    }
}
