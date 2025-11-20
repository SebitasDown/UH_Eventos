package com.UH.OtherLevel.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO {

    Long id;

    @Schema(
            description = "Nombre oficial del lugar",
            example = "Auditorio Central"
    )
    @NotBlank(message = "El nombre del venue no puede estar vacío")
    @NotNull
    private String name;

    @Schema(
            description = "Dirección exacta donde se encuentra ubicado el lugar",
            example = "Calle 45 #22-10, Medellín"
    )
    @NotBlank(message = "La dirección es obligatoria")
    @NotNull
    private String address;

    @Schema(
            description = "Capacidad máxima de personas que puede albergar el lugar",
            example = "350"
    )

    private Integer capacity;
}
