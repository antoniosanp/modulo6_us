package com.example.eventify.service;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventDTOPath;
import com.example.eventify.dto.EventSummaryDTO;
import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Category;
import com.example.eventify.model.Event;
import com.example.eventify.model.Venue;
import com.example.eventify.repository.CategoryRepository;
import com.example.eventify.repository.EventRepository;
import com.example.eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final CategoryRepository categoryRepository;

    public Slice<EventSummaryDTO> searchEvents(String city,
                                               String category,
                                               Integer minCapacity,
                                               LocalDate fromDate,
                                               LocalDate toDate,
                                               Pageable pageable) {
        return eventRepository.searchCatalog(normalize(city), normalize(category), minCapacity, fromDate, toDate, pageable);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No hay un evento con ese id"));
    }

    @Transactional
    public Event addEvent(EventDTO eventDTO) {
        validateUniqueName(eventDTO.getName(), null);

        Event event = new Event();
        applyDto(event, eventDTO.getName(), eventDTO.getEventDate(), eventDTO.getDescription(),
                eventDTO.getVenueId(), eventDTO.getCategoryIds(), true);

        return eventRepository.save(event);
    }

    @Transactional
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
        if (eventDTOPath.getVenueId() != null) {
            event.setVenue(resolveVenue(eventDTOPath.getVenueId()));
        }
        if (eventDTOPath.getCategoryIds() != null) {
            event.setCategories(resolveCategories(eventDTOPath.getCategoryIds(), false));
        }

        return eventRepository.save(event);
    }

    @Transactional
    public Event putEvent(Integer id, EventDTO eventDTO) {
        Event event = getEventById(id);
        validateUniqueName(eventDTO.getName(), id);

        applyDto(event, eventDTO.getName(), eventDTO.getEventDate(), eventDTO.getDescription(),
                eventDTO.getVenueId(), eventDTO.getCategoryIds(), true);

        return eventRepository.save(event);
    }

    @Transactional
    public void deleteAllEvents() {
        List<Event> events = eventRepository.findAll();
        events.forEach(event -> event.setActive(false));
        eventRepository.saveAll(events);
    }

    @Transactional
    public void deleteEventById(Integer id) {
        Event event = getEventById(id);
        event.setActive(false);
        eventRepository.save(event);
    }

    private void validateUniqueName(String name, Integer currentId) {
        Event existing = eventRepository.findByNameIgnoreCase(name).orElse(null);
        if (existing != null && !existing.getId().equals(currentId)) {
            throw new ValidationException("Ya hay un evento con este nombre");
        }
    }

    private void applyDto(Event event,
                          String name,
                          LocalDate eventDate,
                          String description,
                          Integer venueId,
                          List<Integer> categoryIds,
                          boolean requireCategories) {
        event.setName(name);
        event.setEventDate(eventDate);
        event.setDescription(description);
        event.setVenue(resolveVenue(venueId));
        event.setCategories(resolveCategories(categoryIds, requireCategories));
    }

    private Venue resolveVenue(Integer venueId) {
        if (venueId == null) {
            throw new ValidationException("La sede es obligatoria");
        }

        return venueRepository.findById(venueId)
                .orElseThrow(() -> new ValidationException("La sede seleccionada no existe"));
    }

    private Set<Category> resolveCategories(List<Integer> categoryIds, boolean required) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            if (required) {
                throw new ValidationException("Debe seleccionar al menos una categoria");
            }
            return new LinkedHashSet<>();
        }

        Set<Category> categories = new LinkedHashSet<>();
        categoryRepository.findAllById(categoryIds).forEach(categories::add);

        if (categories.size() != categoryIds.stream().distinct().count()) {
            throw new ValidationException("Una o mas categorias seleccionadas no existen");
        }

        return categories;
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
