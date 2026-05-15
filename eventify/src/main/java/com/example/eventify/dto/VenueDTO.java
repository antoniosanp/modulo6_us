package com.example.eventify.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VenueDTO {

    @NotBlank
    String name;

    @NotBlank
    String address;

    @Size(min = 0)
    int maxCapacity;

}
