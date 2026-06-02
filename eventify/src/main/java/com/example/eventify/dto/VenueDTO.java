package com.example.eventify.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VenueDTO {

    @NotBlank(message = "El nombre del venue es obligatorio")
    private String name;

    @NotBlank(message = "La direccion del venue es obligatoria")
    private String address;

    @NotBlank(message = "La ciudad del venue es obligatoria")
    private String city;

    @NotNull(message = "La capacidad maxima es obligatoria")
    @Min(value = 0, message = "La capacidad maxima no puede ser negativa")
    private Integer maxCapacity;
}
