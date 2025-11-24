package com.UH.OtherLevel.application.port.out;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.pageable.PageRequest;
import com.UH.OtherLevel.domain.pageable.PageResult;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);
    Optional<Event> findById(Long id);
    List<Event> findAll();
    boolean deleteById(Long id);
    PageResult<Event> findAllPage(PageRequest pageRequest);
}
