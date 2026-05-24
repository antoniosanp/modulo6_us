package com.example.eventify.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VenueDTOPath {

    private String name;

    private String address;

    @Min(value = 0, message = "La capacidad maxima no puede ser negativa")
    private Integer maxCapacity;
}
