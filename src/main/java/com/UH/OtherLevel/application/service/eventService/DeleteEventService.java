package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.EventNotFoundException;
import com.UH.OtherLevel.application.port.in.eventIn.DeleteEventUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public class DeleteEventService implements DeleteEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;

    public DeleteEventService(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public boolean deleteById(Long id) {
        eventRepositoryPort.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        return eventRepositoryPort.deleteById(id);
    }
}
