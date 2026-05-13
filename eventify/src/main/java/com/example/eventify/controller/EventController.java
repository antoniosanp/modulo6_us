package com.example.eventify.controller;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEventos(){

        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @PostMapping()
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO){
        if(eventService.addEvent(eventDTO) != null)  return ResponseEntity.status(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @DeleteMapping()
    public ResponseEntity<Event> deleteAll(){
        eventService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Integer id){
        Event e = eventService.getEventById(id);

        return ResponseEntity.ok(e);



    }


}
