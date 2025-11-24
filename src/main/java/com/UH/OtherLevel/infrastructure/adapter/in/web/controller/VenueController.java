package com.UH.OtherLevel.infrastructure.adapter.in.web.controller;

import com.UH.OtherLevel.application.service.venueService.*;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.CreateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.UpdateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.venue.VenueResponse;
import com.UH.OtherLevel.infrastructure.adapter.in.web.mapper.VanueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class VenueController {

    private final CreateVenueService createVenueService;
    private final DeleteVenueService deleteVenueService;
    private final FindVenueByIdService findVenueByIdService;
    private final GetAllVenueService getAllVenueService;
    private final UpdateVenueService updateVenueService;
    private final VanueMapper vanueMapper;

    @PostMapping
    public ResponseEntity<VenueResponse> create (@RequestBody CreateVenueRequest request){
        Venue saved = createVenueService.create(vanueMapper.toModel(request));
        return ResponseEntity.ok(vanueMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> findById (@PathVariable Long id){
        Venue venue = findVenueByIdService.findById(id);
        return ResponseEntity.ok(vanueMapper.toResponse(venue));
    }

    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAll(){
        List<Venue> venues = getAllVenueService.getAll();
        return ResponseEntity.ok(vanueMapper.toResponseList(venues));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateVenueRequest request) {

        Venue venueToUpdate = vanueMapper.toUpdateModel(request); // convierte DTO a modelo
        Venue updated = updateVenueService.update(id, venueToUpdate); // pasa id y modelo
        return ResponseEntity.ok(vanueMapper.toResponse(updated));
    }
}
