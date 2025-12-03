package com.UH.OtherLevel.application.port.in.eventIn;

import com.UH.OtherLevel.domain.model.Event;

import java.util.List;

public interface GetAllEventsUseCase {
    List<Event> getEventAll ();
}
