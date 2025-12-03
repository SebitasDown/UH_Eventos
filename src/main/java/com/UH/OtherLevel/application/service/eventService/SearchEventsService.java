package com.UH.OtherLevel.application.service.eventService;

import com.UH.OtherLevel.application.port.in.eventIn.SearchEventUseCase;
import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;

import java.util.List;

public class SearchEventsService implements SearchEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public SearchEventsService(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public List<Event> searchEvents(SearchEvent searchEvent) {
        return eventRepositoryPort.searchEvents(searchEvent);
    }
}
