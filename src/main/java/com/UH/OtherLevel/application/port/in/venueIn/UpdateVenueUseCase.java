package com.UH.OtherLevel.application.port.in.venueIn;

import com.UH.OtherLevel.domain.model.Venue;

public interface UpdateVenueUseCase {
    Venue update(Long id, Venue venue);
}
