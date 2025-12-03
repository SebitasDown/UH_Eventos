package com.UH.OtherLevel.application.port.in.eventIn;

import com.UH.OtherLevel.domain.model.Event;

public interface CreateEventUseCase {
    Event createEvent(Event event);
}
