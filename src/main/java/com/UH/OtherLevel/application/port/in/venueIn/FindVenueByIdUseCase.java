package com.UH.OtherLevel.application.port.in.venueIn;

import com.UH.OtherLevel.domain.model.Venue;

public interface FindVenueByIdUseCase {
    Venue findById(Long id);
}
