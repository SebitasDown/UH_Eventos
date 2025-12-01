package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.eventIn.CreateEventUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
public class CreateEventService implements CreateEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public CreateEventService(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Event createEvent(Event event) {

        if (event.getName() == null || event.getName().isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (event.getVenue().getId() == null || event.getName().isBlank()){
            throw new IllegalArgumentException("El ID del venue no puede ser null");
        }

        Long venueId = event.getVenue().getId();
        Venue venue = venueRepositoryPort.findById(venueId)
                .orElseThrow(() -> new VenueNotFoundException(venueId));
        event.setVenue(venue);

        log.info("USECASE_EVENT_CREATE name={}",event.getName());
        return eventRepositoryPort.save(event);
    }
}
