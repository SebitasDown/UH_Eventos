package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.venueIn.FindVenueByIdUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class FindVenueByIdService implements FindVenueByIdUseCase {
   private final VenueRepositoryPort venueRepositoryPort;

    public FindVenueByIdService(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue findById(Long id) {
        return venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueNotFoundException(id));
    }
}
