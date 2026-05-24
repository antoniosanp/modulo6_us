package com.example.eventify.service;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventDTOPath;
import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No hay un evento con ese id"));
    }

    public Event addEvent(EventDTO eventDTO) {
        validateUniqueName(eventDTO.getName(), null);

        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setEventDate(eventDTO.getEventDate());
        event.setDescription(eventDTO.getDescription());

        return eventRepository.save(event);
    }

    public Event patchEvent(Integer id, EventDTOPath eventDTOPath) {
        Event event = getEventById(id);

        if (eventDTOPath.getName() != null) {
            validateUniqueName(eventDTOPath.getName(), id);
            event.setName(eventDTOPath.getName());
        }
        if (eventDTOPath.getEventDate() != null) {
            event.setEventDate(eventDTOPath.getEventDate());
        }
        if (eventDTOPath.getDescription() != null) {
            event.setDescription(eventDTOPath.getDescription());
        }

        return eventRepository.save(event);
    }

    public Event putEvent(Integer id, EventDTO eventDTO) {
        Event event = getEventById(id);
        validateUniqueName(eventDTO.getName(), id);

        event.setName(eventDTO.getName());
        event.setEventDate(eventDTO.getEventDate());
        event.setDescription(eventDTO.getDescription());

        return eventRepository.save(event);
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public void deleteEventById(Integer id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    private void validateUniqueName(String name, Integer currentId) {
        Event existing = eventRepository.findByName(name);
        if (existing != null && !existing.getId().equals(currentId)) {
            throw new ValidationException("Ya hay un evento con este nombre");
        }
    }
}
