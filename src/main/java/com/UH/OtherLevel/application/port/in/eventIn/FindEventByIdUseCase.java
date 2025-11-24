package com.UH.OtherLevel.application.port.in.eventIn;

import com.UH.OtherLevel.domain.model.Event;

public interface FindEventByIdUseCase {
    Event findById (Long id);
}
