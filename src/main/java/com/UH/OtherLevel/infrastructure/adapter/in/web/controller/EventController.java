package com.UH.OtherLevel.infrastructure.adapter.in.web.controller;

import com.UH.OtherLevel.application.port.in.eventIn.*;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.event.EventResponse;
import com.UH.OtherLevel.infrastructure.adapter.in.web.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<EventResponse> create (@RequestBody CreateEventRequest request){
        Event saved = createEventUseCase.createEvent(eventMapper.toModel(request));
        return ResponseEntity.ok(eventMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById (@PathVariable Long id){
        Event event = findEventByIdUseCase.findById(id);
        return ResponseEntity.ok(eventMapper.toResponse(event));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll(){
        List<Event> events = getAllEventsUseCase.getEventAll();
        return ResponseEntity.ok(eventMapper.toResponseList(events));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateEventRequest request
            ){
        Event updated = updateEventUseCase.update(eventMapper.toUpdateModel(request));
        return ResponseEntity.ok(eventMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        deleteEventUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
