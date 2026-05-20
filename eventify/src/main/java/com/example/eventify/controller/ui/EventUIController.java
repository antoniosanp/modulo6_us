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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/eventos")
public class EventUIController {

    private final EventService eventService;

    @GetMapping("/list")
    public String listarEventosUI(Model model, @PageableDefault(size = 20) Pageable pageable){

        Page<Event> eventos = eventService.getAllEvents(pageable);

        model.addAttribute("eventos", eventos);
        model.addAttribute("tituloPantalla", "lista de eventos");

        return "events/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioEvento(Model model){

        model.addAttribute("evento", new EventDTO());
        model.addAttribute("tituloPantalla", "Registrar nuevo evento");
        return "events/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") EventDTO event){

        eventService.addEvent(event);

        return "redirect:/eventos/list";
    }

}
