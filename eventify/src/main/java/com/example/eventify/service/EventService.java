package com.example.eventify.service;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventDTOPath;
import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event addEvent(EventDTO eventDTO){
        Event e = eventRepository.findByName(eventDTO.getName());

        if (e != null){throw new ValidationException("error");}
        Event eNew = new Event();
        eNew.setName(eventDTO.getName());
        eNew.setEventDate(eventDTO.getEventDate());
        eNew.setDescription(eventDTO.getDescription());


        return eventRepository.save(eNew);
    }

    public List<Event> getAllEvents(){
        List<Event> l = eventRepository.findAll();
        if (l.isEmpty()){throw new ResourceNotFoundException("no hay eventos");
        }

        return l;
    }
    public void deleteAll(){
        eventRepository.deleteAll();
    }

    public Event getEventById(Integer id){
        Event e = eventRepository.findById(id).orElse(null);

        if (e == null) { throw new ResourceNotFoundException("no hay un evento con ese id");}
        return e;
        
    }

    public Event patchEvent(Integer id, EventDTOPath eventDTOPath){
        Event e = eventRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("no hay evento con ese id"));

        if (eventDTOPath.getName() != null) {e.setName(eventDTOPath.getName());}
        if (eventDTOPath.getEventDate() != null) {e.setEventDate(eventDTOPath.getEventDate());}
        if (eventDTOPath.getDescription() != null) {e.setDescription(eventDTOPath.getDescription());}
        eventRepository.save(e);

        return  e;
    }

    public Event putEvent(Integer id, EventDTO eventDTO){

        Event e = eventRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("no hay evento con ese id"));

        if (eventDTO.getName() != null) {e.setName(eventDTO.getName());}
        if (eventDTO.getEventDate() != null) {e.setEventDate(eventDTO.getEventDate());}
        if (eventDTO.getDescription() != null) {e.setDescription(eventDTO.getDescription());}
        eventRepository.save(e);

        return  e;

    }

}
