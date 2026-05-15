package com.example.eventify.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        boolean success,
        String message,
        String details,
        Map<String, String> errors,
        LocalDateTime timestamp
) {

    public static ErrorResponse of(String message, String details) {
        return new ErrorResponse(false, message, details, null, LocalDateTime.now());
    }

    public static ErrorResponse ofValidation(String message, String details, Map<String, String> errors) {
        return new ErrorResponse(false, message, details, errors, LocalDateTime.now());
    }
}
