package com.example.eventify.controller;

import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){this.eventService = eventService;}
    @Operation( summary = "Obtener evento por id", description = "retorna un evento")
    @ApiResponse(responseCode = "200", description = "operación exitosa")
    @GetMapping({"/id"})
    public Event getEvent(@PathVariable int id){
        return eventService.getEventById(id);
    }

    @Operation(summary = "agregar nuevo evento", description =  "retorna un evento")
    @ApiResponse(responseCode = "201", description = "evento agregado correctamente")
    @PostMapping
    public Event postEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }
}
