package com.example.eventify.repository;

import com.example.eventify.model.Venue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VenueRepository {

    private final Map<Integer, Venue> venueMap;

    public VenueRepository(){this.venueMap =   new HashMap<>();}

    public Optional<Venue> getVenueById(int id){
        return Optional.ofNullable(venueMap.get(id));
    }

    public Venue addVenue(Venue venue){

        if (venueMap.containsKey(venue.getId())){throw new RuntimeException("ya hay un destino con esta id");}
        venueMap.put(venue.getId(),venue);

        return venue;
    }
}
