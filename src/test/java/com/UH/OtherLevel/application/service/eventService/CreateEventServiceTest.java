package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEventServiceTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @Mock
    private VenueRepositoryPort venueRepositoryPort;

    private CreateEventService createEventService;

    @BeforeEach
    void setUp() {
        createEventService = new CreateEventService(eventRepositoryPort, venueRepositoryPort);
    }

    @Test
    void createEvent_Success() {
        // Arrange
        Long venueId = 1L;
        Venue venue = new Venue();
        venue.setId(venueId);

        Event event = new Event();
        event.setName("Test Event");
        event.setVenue(venue);

        when(venueRepositoryPort.findById(venueId)).thenReturn(Optional.of(venue));
        when(eventRepositoryPort.save(any(Event.class))).thenReturn(event);

        // Act
        Event createdEvent = createEventService.createEvent(event);

        // Assert
        assertNotNull(createdEvent);
        assertEquals("Test Event", createdEvent.getName());
        assertEquals(venueId, createdEvent.getVenue().getId());
        verify(venueRepositoryPort).findById(venueId);
        verify(eventRepositoryPort).save(event);
    }

    @Test
    void createEvent_ThrowsException_WhenNameIsBlank() {
        // Arrange
        Event event = new Event();
        event.setName("");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createEventService.createEvent(event);
        });
        assertEquals("El nombre no puede estar vacio", exception.getMessage());
        verifyNoInteractions(venueRepositoryPort);
        verifyNoInteractions(eventRepositoryPort);
    }

    @Test
    void createEvent_ThrowsException_WhenVenueIdIsNull() {
        // Arrange
        Event event = new Event();
        event.setName("Test Event");
        event.setVenue(new Venue()); // ID is null

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createEventService.createEvent(event);
        });
        assertEquals("El ID del venue no puede ser null", exception.getMessage());
        verifyNoInteractions(venueRepositoryPort);
        verifyNoInteractions(eventRepositoryPort);
    }

    @Test
    void createEvent_ThrowsException_WhenVenueNotFound() {
        // Arrange
        Long venueId = 1L;
        Venue venue = new Venue();
        venue.setId(venueId);

        Event event = new Event();
        event.setName("Test Event");
        event.setVenue(venue);

        when(venueRepositoryPort.findById(venueId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(VenueNotFoundException.class, () -> {
            createEventService.createEvent(event);
        });
        verify(venueRepositoryPort).findById(venueId);
        verifyNoInteractions(eventRepositoryPort);
    }
}
