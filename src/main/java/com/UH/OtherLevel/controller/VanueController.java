package com.UH.OtherLevel.controller;

import com.UH.OtherLevel.dto.error.ErrorDTO;
import com.UH.OtherLevel.dto.VenueDTO;
import com.UH.OtherLevel.mapper.VanueMapper;
import com.UH.OtherLevel.model.Venue;
import com.UH.OtherLevel.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VanueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<?> crearLugares (@RequestBody VenueDTO venueDTO){
        Venue venue = venueService.create(VanueMapper.INSTANCE.toModel(venueDTO));
        VenueDTO venueCreated = VanueMapper.INSTANCE.toDTO(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(venueCreated);
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> obtenerTodosLosLugares (){
        List<Venue> venue = venueService.getVanueAll();
        List<VenueDTO> venueDTOList = VanueMapper.INSTANCE.toDTOList(venue);
        return ResponseEntity.ok(venueDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId (@PathVariable Long id){
        Venue venue = venueService.findById(id);
        VenueDTO res = VanueMapper.INSTANCE.toDTO(venue);


        return ResponseEntity.ok(res);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarLugar (@PathVariable Long id, @RequestBody VenueDTO venueDTO){
        Venue venue = venueService.update(id, VanueMapper.INSTANCE.toModel(venueDTO));
        return ResponseEntity.ok(VanueMapper.INSTANCE.toDTO(venue));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLugar (@PathVariable Long id){
        boolean eliminado = venueService.deleteById(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el venue con id: " + id);
        }

        return ResponseEntity.ok("Evento eliminado");
    }
}
