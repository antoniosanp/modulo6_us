package com.example.eventify.controller;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventDTOPath;
import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDTO eventDTO){
        Event event = eventService.addEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);


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

    @PatchMapping("/{id}")
    public ResponseEntity<Event> parcialUpdate(@PathVariable Integer id, @RequestBody EventDTOPath eventDTOPath){
        Event e = eventService.patchEvent(id,eventDTOPath);

        return ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> uptade(@PathVariable Integer id, @Valid @RequestBody EventDTO eventDTO){
        Event e = eventService.putEvent(id, eventDTO);

        return ResponseEntity.ok(e);
    }


}
