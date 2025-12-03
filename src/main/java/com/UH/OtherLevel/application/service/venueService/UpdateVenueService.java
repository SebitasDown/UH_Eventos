package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.venueIn.UpdateVenueUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class UpdateVenueService implements UpdateVenueUseCase {
    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateVenueService(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue update(Long id, Venue venue) {
        Venue exist = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new VenueNotFoundException(id));

        exist.setName(venue.getName());
        exist.setAddress(venue.getAddress());
        exist.setCapacity(venue.getCapacity());

        return venueRepositoryPort.save(exist);

    }
}
