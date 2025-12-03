package com.UH.OtherLevel.application.port.out;

import com.UH.OtherLevel.domain.model.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    Optional<Venue> findById(Long id);
    List<Venue> findAll();
    boolean deleteById(Long id);
}
