package com.example.eventify.controller;

import com.example.eventify.dto.ApiResponse;
import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventDTOPath;
import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Listar todos los eventos", description = "Retorna el catalogo paginado de eventos")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Event>>> getAllEvents(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<Event> events = eventService.getAllEvents(pageable);
        return ResponseEntity.ok(ApiResponse.success("Eventos consultados correctamente", events));
    }

    @Operation(summary = "Obtener evento por id", description = "Retorna un evento especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(ApiResponse.success("Evento encontrado correctamente", event));
    }

    @Operation(summary = "Registrar nuevo evento", description = "Crea un nuevo evento")
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        Event event = eventService.addEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Evento registrado correctamente", event));
    }

    @Operation(summary = "Actualizar parcialmente un evento", description = "Actualiza los campos enviados en el body")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> patchEvent(@PathVariable Integer id,
                                                         @RequestBody EventDTOPath eventDTOPath) {
        Event event = eventService.patchEvent(id, eventDTOPath);
        return ResponseEntity.ok(ApiResponse.success("Evento actualizado correctamente", event));
    }

    @Operation(summary = "Reemplazar un evento", description = "Actualiza completamente un evento")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> putEvent(@PathVariable Integer id,
                                                       @Valid @RequestBody EventDTO eventDTO) {
        Event event = eventService.putEvent(id, eventDTO);
        return ResponseEntity.ok(ApiResponse.success("Evento actualizado correctamente", event));
    }

    @Operation(summary = "Eliminar todos los eventos", description = "Borra el catalogo completo de eventos")
    @DeleteMapping()
    public ResponseEntity<ApiResponse<Void>> deleteAllEvents() {
        eventService.deleteAllEvents();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("Eventos eliminados correctamente", null));
    }

    @Operation(summary = "Eliminar un evento por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("Evento eliminado correctamente", null));
    }
}
