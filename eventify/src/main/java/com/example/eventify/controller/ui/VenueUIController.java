package com.example.eventify.controller.ui;

import com.example.eventify.dto.VenueDTO;
import com.example.eventify.model.Venue;
import com.example.eventify.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/venues")
public class VenueUIController {

    private final VenueService venueService;

    @GetMapping
    public String listVenues(Model model, @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<Venue> venues = venueService.getAllVenues(pageable);
        model.addAttribute("page", venues);
        model.addAttribute("title", "Listado de venues");
        return "venues/lista";
    }

    @GetMapping("/new")
    public String showVenueForm(Model model) {
        model.addAttribute("venue", new VenueDTO());
        model.addAttribute("title", "Registrar venue");
        return "venues/formulario";
    }

    @PostMapping
    public String saveVenue(@ModelAttribute("venue") VenueDTO venueDTO) {
        venueService.addVenue(venueDTO);
        return "redirect:/ui/venues";
    }
}
