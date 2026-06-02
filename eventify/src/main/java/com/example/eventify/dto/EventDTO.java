package com.example.eventify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventDTO {

    @NotBlank(message = "El nombre del evento es obligatorio")
    private String name;

    @NotNull(message = "La fecha del evento es obligatoria")
    private LocalDate eventDate;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "La sede es obligatoria")
    private Integer venueId;

    @NotEmpty(message = "Debe seleccionar al menos una categoria")
    private List<Integer> categoryIds;

}
