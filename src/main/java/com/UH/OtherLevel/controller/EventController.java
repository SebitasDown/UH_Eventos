package com.UH.OtherLevel.controller;

import com.UH.OtherLevel.dto.EventDTO;
import com.UH.OtherLevel.mapper.EventMapper;
import com.UH.OtherLevel.model.Event;
import com.UH.OtherLevel.service.EventService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDTO> crearEvento (@RequestBody EventDTO eventDTO){
        Event event = eventService.create(EventMapper.INSTANCE.toModel(eventDTO));
        // Mejor practica crear un DTO de respuesta Uno para la peticion y la respuesta

        if (event == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        EventDTO eventCreate = EventMapper.INSTANCE.toDTO(event);


        // Mejor forma y mas limpia
       /* EventDTO eventCreated2 = EventMapper.INSTANCE.
                toDTO(eventService.
                        create(EventMapper.INSTANCE.toModel(eventDTO)));*/

        return ResponseEntity.ok(eventCreate);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> obtenerTodosLosEventos (){
        List<Event> events = eventService.getEventAll();
        if(events == null || events.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<EventDTO> eventDTOList = EventMapper.INSTANCE.toDTOList(events);
        return  ResponseEntity.ok(eventDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId (@PathVariable Long id){
        Event event = eventService.findById(id);

        EventDTO eventDTO = EventMapper.INSTANCE.toDTO(event);
        return ResponseEntity.ok(eventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> editarEvento (@PathVariable Long id, @RequestBody EventDTO eventDTO){

        Event event = EventMapper.INSTANCE.toModel(eventDTO);
        event.setId(id);

        Event updatedEvent = eventService.update(event);

        EventDTO res = EventMapper.INSTANCE.toDTO(updatedEvent);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEvento (@PathVariable Long id){
        boolean eliminado = eventService.deleteById(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el evento con id: " + id);
        }

        return ResponseEntity.ok("Evento eliminado");
    }

    // Metodo con pageable
    @GetMapping("/pageable")
    public ResponseEntity<Page<EventDTO>> getEvents (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size){

        Page<Event> events = eventService.getEventAll(page, size);
        Page<EventDTO> dtoPage = events.map(EventMapper.INSTANCE::toDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dtoPage);
    }
}
