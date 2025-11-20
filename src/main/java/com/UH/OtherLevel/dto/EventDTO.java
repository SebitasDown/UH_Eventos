package com.UH.OtherLevel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    Long id;

    @Schema(
            description = "Nombre oficial del evento",
            example = "Concierto de Temporada"
    )
    @NotBlank(message = "El nombre no puede estar vacio")
    @NotNull
    private String name;

    @Schema(
            description = "Fecha y hora programada del evento en formato ISO 8601",
            example = "2025-03-15T19:30:00"
    )
    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime date;

    @Schema(
            description = "Identificador del lugar donde se realizará el evento",
            example = "1"
    )
    @NotNull(message = "Lugar obligatorio")
    private Long venueId;
}
