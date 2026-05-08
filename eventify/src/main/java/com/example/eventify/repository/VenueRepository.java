package com.example.eventify.repository;

import com.example.eventify.model.Venue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class VenueRepository {

    private final Map<Integer, Venue> venueMap;

    public VenueRepository() {
        this.venueMap = new LinkedHashMap<>();
    }

    public List<Venue> getAllVenues() {
        return new ArrayList<>(venueMap.values());
    }

    public Optional<Venue> getVenueById(int id) {
        return Optional.ofNullable(venueMap.get(id));
    }

    public Venue addVenue(Venue venue) {
        venueMap.put(venue.getId(), venue);
        return venue;
    }
}
