package com.UH.OtherLevel.application.port.in.eventIn;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.pageable.PageRequest;
import com.UH.OtherLevel.domain.pageable.PageResult;

public interface GetAllEventsPagedUseCase {
    PageResult<Event> getEventAll(PageRequest pageRequest);
}
