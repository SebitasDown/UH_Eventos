package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.venueIn.CreateVenueUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateVenueService implements CreateVenueUseCase {
    private final VenueRepositoryPort venueRepositoryPort;
    @Override
    public Venue create(Venue venue) {
        if (venue == null) {
            throw new IllegalArgumentException("Venue no puede ser null");
        }

        if (venue.getName() == null || venue.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (venue.getCapacity() == null || venue.getCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }

        return venueRepositoryPort.save(venue);
    }
}
