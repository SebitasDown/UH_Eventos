package com.UH.OtherLevel.application.service.venueService;

import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.venueIn.GetAllVenueUseCase;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllVenueService implements GetAllVenueUseCase {
   private final VenueRepositoryPort venueRepositoryPort;
    @Override
    public List<Venue> getAll() {
        return venueRepositoryPort.findAll();
    }
}
