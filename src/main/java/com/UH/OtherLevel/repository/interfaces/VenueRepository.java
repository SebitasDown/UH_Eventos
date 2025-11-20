package com.UH.OtherLevel.repository.interfaces;

import com.UH.OtherLevel.model.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepository {
    List<Venue> findAll();
    Optional<Venue> findById(Long id);
    Venue save (Venue venue);
    boolean deleteById (Long id);
    Venue update(Venue venue);
}
