package com.example.eventify.repository;

import com.example.eventify.model.Event;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EventRepository {

    private final Map<Integer, Event> eventMap;

    public EventRepository(){
        this.eventMap = new HashMap<>();
    }

    public Optional<Event> getEventById(int id){
        return Optional.ofNullable(eventMap.get(id));
    }

    public  Event addEvent(Event event){
        if (getEventById(event.getId()).isPresent()){ throw new RuntimeException("ya hay un evento registrado con esta id");}
            eventMap.put(event.getId(), event);
        return event;}




}
