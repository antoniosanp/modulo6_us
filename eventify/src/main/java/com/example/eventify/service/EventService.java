package com.example.eventify.service;

import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){this.eventRepository = eventRepository;}

    public Event getEventById(int id){
        if (eventRepository.getEventById(id).isEmpty()) {throw new RuntimeException("No hay evento con esa id");}
        return eventRepository.getEventById(id).get();
    }

    public Event addEvent(Event event){
        if (eventRepository.getEventById(event.getId()).isPresent()){throw new RuntimeException("ya hay un evento con esa id");}
        eventRepository.addEvent(event);
        return event;
    }

}
