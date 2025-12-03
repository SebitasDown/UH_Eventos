package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.EventNotFoundException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.application.port.in.eventIn.UpdateEventUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class UpdateEventService implements UpdateEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateEventService(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Event update(Long id, Event event) {


        Event exist = eventRepositoryPort.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));


        if (event.getDate() == null ||
                event.getName() == null ||
                event.getName().trim().isEmpty() ||
                event.getVenue() == null){
            throw new IllegalArgumentException("Los campos no pueden estar vacíos");
        }


        Long venueId = event.getVenue().getId();
        Venue venue = venueRepositoryPort.findById(venueId)
                .orElseThrow(() -> new VenueNotFoundException(venueId));


        exist.setName(event.getName());
        exist.setDate(event.getDate());
        exist.setDescription(event.getDescription());
        exist.setVenue(venue);

        return eventRepositoryPort.save(exist);
    }

}
