package com.example.eventify.controller.ui;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/events")
public class EventUIController {

    private final EventService eventService;

    @GetMapping
    public String listEvents(Model model, @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<Event> events = eventService.getAllEvents(pageable);
        model.addAttribute("page", events);
        model.addAttribute("title", "Listado de eventos");
        return "events/lista";
    }

    @GetMapping("/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new EventDTO());
        model.addAttribute("title", "Registrar evento");
        return "events/formulario";
    }

    @PostMapping
    public String saveEvent(@ModelAttribute("event") EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
        return "redirect:/ui/events";
    }
}
