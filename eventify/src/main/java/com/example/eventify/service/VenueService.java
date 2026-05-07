package com.example.eventify.service;

import com.example.eventify.model.Venue;
import com.example.eventify.repository.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository){this.venueRepository = venueRepository;}

    public Venue getVenueById(int id){
        return venueRepository.getVenueById(id)
                .orElseThrow(()-> new RuntimeException("No hay destino con esta id"));
    }

    public Venue addVenue(Venue venue){

        if (venueRepository.getVenueById(venue.getId()).isPresent()) {
            throw new RuntimeException("Ya hay un destino con esta id");
        }

        return venueRepository.addVenue(venue);
    }

}



