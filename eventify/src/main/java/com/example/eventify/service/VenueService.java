package com.example.eventify.service;

import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Venue;
import com.example.eventify.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<Venue> getAllVenues() {
        return venueRepository.getAllVenues();
    }

    public Venue getVenueById(int id) {
        return venueRepository.getVenueById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un venue con id " + id));
    }

    public Venue addVenue(Venue venue) {
        validateVenue(venue);

        if (venueRepository.getVenueById(venue.getId()).isPresent()) {
            throw new ValidationException("Ya existe un venue registrado con id " + venue.getId());
        }

        return venueRepository.addVenue(venue);
    }

    private void validateVenue(Venue venue) {
        if (venue == null) {
            throw new ValidationException("El cuerpo de la solicitud del venue es obligatorio");
        }
        if (venue.getId() == null) {
            throw new ValidationException("El id del venue es obligatorio");
        }
        if (venue.getName() == null || venue.getName().isBlank()) {
            throw new ValidationException("El nombre del venue no puede estar vacio");
        }
        if (venue.getAddress() == null || venue.getAddress().isBlank()) {
            throw new ValidationException("La direccion del venue no puede estar vacia");
        }
        if (venue.getMaxCapacity() <= 0) {
            throw new ValidationException("La capacidad del venue debe ser mayor a cero");
        }
    }
}
