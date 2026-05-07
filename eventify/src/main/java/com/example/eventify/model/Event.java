package com.example.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    Integer id;
    String name;
    LocalDate eventDate;
    String description;


}
