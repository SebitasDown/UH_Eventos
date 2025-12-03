package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.domain.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllEventsServiceTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    private GetAllEventsService getAllEventsService;

    @BeforeEach
    void setUp() {
        getAllEventsService = new GetAllEventsService(eventRepositoryPort);
    }

    @Test
    void getEventAll_ReturnsListOfEvents() {
        // Arrange
        Event event1 = new Event();
        event1.setId(1L);
        Event event2 = new Event();
        event2.setId(2L);
        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepositoryPort.findAll()).thenReturn(events);

        // Act
        List<Event> result = getAllEventsService.getEventAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventRepositoryPort).findAll();
    }
}
