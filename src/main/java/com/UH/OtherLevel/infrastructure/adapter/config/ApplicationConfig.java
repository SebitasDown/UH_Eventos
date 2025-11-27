package com.UH.OtherLevel.infrastructure.adapter.config;

import com.UH.OtherLevel.application.port.in.eventIn.*;
import com.UH.OtherLevel.application.port.in.venueIn.*;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import com.UH.OtherLevel.application.service.eventService.*;
import com.UH.OtherLevel.application.service.venueService.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public CreateEventUseCase createEventUseCase(
            EventRepositoryPort eventRepositoryPort,
            VenueRepositoryPort venueRepositoryPort) {
        return new CreateEventService(eventRepositoryPort, venueRepositoryPort);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new DeleteEventService(eventRepositoryPort);
    }

    @Bean
    public FindEventByIdUseCase findEventByIdUseCase(EventRepositoryPort eventRepositoryPort) {
        return new FindEventByIdService(eventRepositoryPort);
    }

    @Bean
    public GetAllEventsUseCase getAllEventsUseCase(EventRepositoryPort eventRepositoryPort) {
        return new GetAllEventsService(eventRepositoryPort);
    }

    @Bean
    public UpdateEventUseCase updateEventUseCase(
            EventRepositoryPort eventRepositoryPort,
            VenueRepositoryPort venueRepositoryPort) {
        return new UpdateEventService(eventRepositoryPort, venueRepositoryPort);
    }

    // Venue Use Cases
    @Bean
    public CreateVenueUseCase createVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new CreateVenueService(venueRepositoryPort);
    }

    @Bean
    public DeleteVenueUseCase deleteVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new DeleteVenueService(venueRepositoryPort);
    }

    @Bean
    public FindVenueByIdUseCase findVenueByIdUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new FindVenueByIdService(venueRepositoryPort);
    }

    @Bean
    public GetAllVenueUseCase getAllVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new GetAllVenueService(venueRepositoryPort);
    }

    @Bean
    public UpdateVenueUseCase updateVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new UpdateVenueService(venueRepositoryPort);
    }

    @Bean
    public SearchEventUseCase searchEventUseCase(EventRepositoryPort eventRepositoryPort){
        return new SearchEventsService(eventRepositoryPort);
    }
}
