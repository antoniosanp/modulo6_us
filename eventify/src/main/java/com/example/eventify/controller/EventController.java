package com.example.eventify.controller;

import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){this.eventService = eventService;}

    @GetMapping({"/id"})
    public Event getEvent(@PathVariable int id){
        return eventService.getEventById(id);
    }

    @PostMapping
    public Event postEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }
}
