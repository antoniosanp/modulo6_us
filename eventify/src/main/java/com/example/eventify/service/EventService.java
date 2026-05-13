package com.example.eventify.service;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    Event addEvent(EventDTO eventDTO){
        Event e = eventRepository.findByName(eventDTO.getName());

        if (e != null){throw new RuntimeException("Ya hay un evento con este nombre");}
        Event eNew = new Event();
        eNew.setName(eventDTO.getName());
        eNew.setEventDate(eventDTO.getEventDate());
        eNew.setDescription(eventDTO.getDescription());


        return eventRepository.save(eNew);
    }

    List<Event> getAllEvents(){

        return eventRepository.findAll();
    }

    

}
