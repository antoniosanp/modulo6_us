package com.example.eventify.dto;

import java.time.LocalDate;

public record EventSummaryDTO(
        String eventName,
        LocalDate eventDate,
        String venueName,
        String city
) {
}
