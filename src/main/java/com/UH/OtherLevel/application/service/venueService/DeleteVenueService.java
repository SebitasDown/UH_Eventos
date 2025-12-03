package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.application.port.in.venueIn.DeleteVenueUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class DeleteVenueService implements DeleteVenueUseCase {
    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteVenueService(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public boolean deleteById(Long id) {

        venueRepositoryPort.findById(id)
                .orElseThrow(()-> new VenueNotFoundException(id));
        return venueRepositoryPort.deleteById(id);
    }
}
