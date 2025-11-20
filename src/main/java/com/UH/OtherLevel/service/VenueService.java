package com.UH.OtherLevel.service;

import com.UH.OtherLevel.model.Event;
import com.UH.OtherLevel.model.Venue;

import java.util.List;

public interface VenueService {
    Venue create (Venue venue);
    Venue update (Long id, Venue venue);
    List<Venue> getVanueAll ();
    boolean deleteById (Long id);
    Venue findById (Long id);
}
