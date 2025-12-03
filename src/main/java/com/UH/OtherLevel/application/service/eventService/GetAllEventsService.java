package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.application.port.in.eventIn.GetAllEventsUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public class GetAllEventsService implements GetAllEventsUseCase {
   private final EventRepositoryPort eventRepositoryPort;

    public GetAllEventsService(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public List<Event> getEventAll() {
        return eventRepositoryPort.findAll();
    }
}
