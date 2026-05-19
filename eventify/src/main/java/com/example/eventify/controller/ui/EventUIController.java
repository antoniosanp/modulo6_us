package com.example.eventify.controller.ui;


import com.example.eventify.model.Event;
import com.example.eventify.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/eventos/lista")
public class EventUIController {

    private final EventService eventService;

    @GetMapping
    public String listarEventosUI(Model model, Pageable pageable){
        Page<Event> eventos = eventService.getAllEvents(pageable);

        model.addAttribute("eventos", eventos);
        model.addAttribute("tituloPantalla", "lista de eventos");

        return "events/lista";

    }


}
