package com.example.eventify.repository;

import com.example.eventify.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EventRepository {

    private final Map<Integer, Event> eventMap;

    public EventRepository() {
        this.eventMap = new LinkedHashMap<>();
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(eventMap.values());
    }

    public Optional<Event> getEventById(int id) {
        return Optional.ofNullable(eventMap.get(id));
    }

    public Event addEvent(Event event) {
        eventMap.put(event.getId(), event);
        return event;
    }
}
