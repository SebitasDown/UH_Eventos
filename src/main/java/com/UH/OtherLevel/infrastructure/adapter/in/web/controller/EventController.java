package com.UH.OtherLevel.infrastructure.adapter.in.web.controller;

import com.UH.OtherLevel.application.port.in.eventIn.*;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.SearchEventsCriteria;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.event.EventResponse;
import com.UH.OtherLevel.infrastructure.adapter.in.web.mapper.EventMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter.config.TransactionalUseCaseExecutor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final FindEventByIdUseCase findEventByIdUseCase;
    private final GetAllEventsUseCase getAllEventsUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final SearchEventUseCase searchEventUseCase;
    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody CreateEventRequest request) {
        Event saved = transactionalUseCaseExecutor.executeInTransaction(() ->
                createEventUseCase.createEvent(eventMapper.toModel(request))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(eventMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable Long id) {
        Event event = transactionalUseCaseExecutor.executeReadOnly(() ->
                findEventByIdUseCase.findById(id)
        );
        return ResponseEntity.ok(eventMapper.toResponse(event));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {
        List<Event> events = transactionalUseCaseExecutor.executeReadOnly(() ->
                getAllEventsUseCase.getEventAll()
        );
        return ResponseEntity.ok(eventMapper.toResponseList(events));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEventRequest request) {
        Event updated = transactionalUseCaseExecutor.executeInTransaction(() ->
                updateEventUseCase.update(id, eventMapper.toUpdateModel(request))
        );
        return ResponseEntity.ok(eventMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionalUseCaseExecutor.executeInTransactionVoid(() ->
                deleteEventUseCase.deleteById(id)
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventResponse>> search(
            @RequestParam(required = false) Long venueId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(required = false) String name) {
        SearchEventsCriteria criteria = new SearchEventsCriteria(venueId, dateFrom, name);
        SearchEvent searchEvent = eventMapper.toModel(criteria);
        List<Event> events = searchEventUseCase.searchEvents(searchEvent);
        return ResponseEntity.ok(eventMapper.toResponseList(events));
    }
}