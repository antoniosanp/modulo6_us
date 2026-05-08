package com.example.eventify.controller;

import com.example.eventify.dto.ApiResponse;
import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Listar todos los eventos", description = "Retorna el catalogo completo de eventos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(ApiResponse.success("Eventos consultados correctamente", events));
    }

    @Operation(summary = "Obtener evento por id", description = "Retorna un evento especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEvent(@PathVariable int id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(ApiResponse.success("Evento encontrado correctamente", event));
    }

    @Operation(summary = "Registrar nuevo evento", description = "Crea un nuevo evento en memoria")
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> postEvent(@RequestBody Event event) {
        Event savedEvent = eventService.addEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Evento registrado correctamente", savedEvent));
    }
}
