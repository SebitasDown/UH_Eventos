package com.UH.OtherLevel.infrastructure.adapter.in.web.controller;

import com.UH.OtherLevel.application.port.in.venueIn.*;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.CreateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.UpdateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.venue.VenueResponse;
import com.UH.OtherLevel.infrastructure.adapter.in.web.mapper.VanueMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter.config.TransactionalUseCaseExecutor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
@Validated
public class VenueController {

    private final CreateVenueUseCase createVenue;
    private final DeleteVenueUseCase deleteVenue;
    private final FindVenueByIdUseCase findVenueById;
    private final GetAllVenueUseCase getAllVenue;
    private final UpdateVenueUseCase updateVenue;
    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;
    private final VanueMapper vanueMapper;

    @PostMapping
    public ResponseEntity<VenueResponse> create(@Valid @RequestBody CreateVenueRequest request) {
        log.info("VENUE_CREATE_REQUEST name={}", request.getName());
        Venue saved = transactionalUseCaseExecutor
                .executeInTransaction(() -> createVenue.create(vanueMapper.toModel(request)));
        log.info("VENUE_CREATE_SUCCESS id={}", saved.getId());
        return ResponseEntity.ok(vanueMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> findById(@PathVariable Long id) {
        log.info("VENUE_FIND_BY_ID_REQUEST id={}", id);
        Venue venue = transactionalUseCaseExecutor.executeReadOnly(() -> findVenueById.findById(id));
        log.info("VENUE_FIND_BY_ID_SUCCESS id={}", venue.getId());
        return ResponseEntity.ok(vanueMapper.toResponse(venue));
    }

    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAll() {
        log.info("VENUE_GET_ALL_REQUEST");
        List<Venue> venues = transactionalUseCaseExecutor.executeReadOnly(() -> getAllVenue.getAll());
        log.info("VENUE_GET_ALL_SUCCESS count={}", venues.size());
        return ResponseEntity.ok(vanueMapper.toResponseList(venues));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateVenueRequest request) {
        log.info("VENUE_UPDATE_REQUEST id={} name={}", id, request.getName());
        Venue updated = transactionalUseCaseExecutor.executeInTransaction(() -> {
            Venue venueToUpdate = vanueMapper.toUpdateModel(request);
            return updateVenue.update(id, venueToUpdate);
        });
        log.info("VENUE_UPDATE_SUCCESS id={}", updated.getId());
        return ResponseEntity.ok(vanueMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("VENUE_DELETE_REQUEST id={}", id);
        boolean deleted = transactionalUseCaseExecutor.executeInTransaction(() -> deleteVenue.deleteById(id));

        if (!deleted) {
            log.warn("VENUE_DELETE_NOT_FOUND id={}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("VENUE_DELETE_SUCCESS id={}", id);
        return ResponseEntity.noContent().build();
    }
}
