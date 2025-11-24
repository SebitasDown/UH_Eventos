package com.UH.OtherLevel.application.port.in.venueIn;

import com.UH.OtherLevel.domain.model.Venue;

import java.util.List;

public interface GetAllVenueUseCase {
    List<Venue> getAll();
}
