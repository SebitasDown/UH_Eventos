package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.venueIn.UpdateVenueUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVenueService implements UpdateVenueUseCase {
    private VenueRepositoryPort venueRepositoryPort;

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
