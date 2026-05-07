package com.example.eventify.controller;


import com.example.eventify.model.Venue;
import com.example.eventify.service.VenueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService){ this.venueService = venueService;}


    @GetMapping("/{id}")
    public Venue getVenue(@PathVariable int id){
        return venueService.getVenueById(id);
    }

    @PostMapping
    public Venue postVenue(@RequestBody Venue venue){
        return venueService.addVenue(venue);
    }

}
