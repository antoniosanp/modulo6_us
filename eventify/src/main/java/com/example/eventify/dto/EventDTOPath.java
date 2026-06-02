package com.example.eventify.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class EventDTOPath {


    private String name;

    private LocalDate eventDate;

    private String description;

    private Integer venueId;

    private List<Integer> categoryIds;

}
