package com.example.eventify.controller.ui;

import com.example.eventify.dto.EventDTO;
import com.example.eventify.dto.EventSummaryDTO;
import com.example.eventify.service.CategoryService;
import com.example.eventify.service.EventService;
import com.example.eventify.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/events")
public class EventUIController {

    private final EventService eventService;
    private final VenueService venueService;
    private final CategoryService categoryService;

    @GetMapping
    public String listEvents(Model model,
                             @RequestParam(required = false) String city,
                             @RequestParam(required = false) String category,
                             @RequestParam(required = false) Integer minCapacity,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             @RequestParam(required = false) LocalDate fromDate,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                             @RequestParam(required = false) LocalDate toDate,
                             @PageableDefault(size = 12, sort = "eventDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Slice<EventSummaryDTO> events = eventService.searchEvents(city, category, minCapacity, fromDate, toDate, pageable);
        model.addAttribute("slice", events);
        model.addAttribute("city", city);
        model.addAttribute("category", category);
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("title", "Listado de eventos");
        return "events/lista";
    }

    @GetMapping("/new")
    public String showEventForm(Model model) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setCategoryIds(new ArrayList<>());
        model.addAttribute("event", eventDTO);
        model.addAttribute("venues", venueService.getAllVenuesForSelection());
        model.addAttribute("categories", categoryService.getAllCategoriesForSelection());
        model.addAttribute("title", "Registrar evento");
        return "events/formulario";
    }

    @PostMapping
    public String saveEvent(@ModelAttribute("event") EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
        return "redirect:/ui/events";
    }
}
