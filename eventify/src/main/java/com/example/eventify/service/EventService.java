package com.example.eventify.service;

import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public Event getEventById(int id) {
        return eventRepository.getEventById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un evento con id " + id));
    }

    public Event addEvent(Event event) {
        validateEvent(event);

        if (eventRepository.getEventById(event.getId()).isPresent()) {
            throw new ValidationException("Ya existe un evento registrado con id " + event.getId());
        }

        return eventRepository.addEvent(event);
    }

    private void validateEvent(Event event) {
        if (event == null) {
            throw new ValidationException("El cuerpo de la solicitud del evento es obligatorio");
        }
        if (event.getId() == null) {
            throw new ValidationException("El id del evento es obligatorio");
        }
        if (event.getName() == null || event.getName().isBlank()) {
            throw new ValidationException("El nombre del evento no puede estar vacio");
        }
        if (event.getEventDate() == null) {
            throw new ValidationException("La fecha del evento es obligatoria");
        }
        if (event.getDescription() == null || event.getDescription().isBlank()) {
            throw new ValidationException("La descripcion del evento no puede estar vacia");
        }
    }
}
