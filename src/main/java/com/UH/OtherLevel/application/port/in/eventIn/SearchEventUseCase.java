package com.UH.OtherLevel.application.port.in.eventIn;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;

import java.util.List;

public interface SearchEventUseCase {
    List<Event> searchEvents(SearchEvent searchEvent);
}
