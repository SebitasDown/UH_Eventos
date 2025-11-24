package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.EventNotFoundException;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.application.port.in.eventIn.FindEventByIdUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindEventByIdService implements FindEventByIdUseCase {
   private final EventRepositoryPort eventRepositoryPort;
    @Override
    public Event findById(Long id) {
        return eventRepositoryPort.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

    }
}
