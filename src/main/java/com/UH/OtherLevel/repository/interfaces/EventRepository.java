package com.UH.OtherLevel.repository.interfaces;

import com.UH.OtherLevel.model.Event;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    List<Event> findAll();
    Page<Event> findAll(int page, int size);
    Optional<Event> findById(Long id);
    Event save(Event event);
    boolean deleteById (Long id);
    Event update(Event event);
}
