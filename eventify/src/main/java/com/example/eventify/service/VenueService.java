package com.example.eventify.service;

import com.example.eventify.dto.VenueDTO;
import com.example.eventify.dto.VenueDTOPath;
import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Venue;
import com.example.eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public Page<Venue> getAllVenues(Pageable pageable) {
        return venueRepository.findAll(pageable);
    }

    public List<Venue> getAllVenuesForSelection() {
        return venueRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Venue getVenueById(Integer id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No hay venue con ese id"));
    }

    @Transactional
    public Venue addVenue(VenueDTO venueDTO) {
        validateUniqueAddress(venueDTO.getAddress(), null);

        Venue venue = new Venue();
        venue.setName(venueDTO.getName());
        venue.setCity(venueDTO.getCity());
        venue.setAddress(venueDTO.getAddress());
        venue.setMaxCapacity(venueDTO.getMaxCapacity());
        return venueRepository.save(venue);
    }

    @Transactional
    public Venue patchVenue(Integer id, VenueDTOPath venueDTOPath) {
        Venue venue = getVenueById(id);

        if (venueDTOPath.getName() != null) {
            venue.setName(venueDTOPath.getName());
        }
        if (venueDTOPath.getCity() != null) {
            venue.setCity(venueDTOPath.getCity());
        }
        if (venueDTOPath.getAddress() != null) {
            validateUniqueAddress(venueDTOPath.getAddress(), id);
            venue.setAddress(venueDTOPath.getAddress());
        }
        if (venueDTOPath.getMaxCapacity() != null) {
            venue.setMaxCapacity(venueDTOPath.getMaxCapacity());
        }

        return venueRepository.save(venue);
    }

    @Transactional
    public Venue putVenue(Integer id, VenueDTO venueDTO) {
        Venue venue = getVenueById(id);
        validateUniqueAddress(venueDTO.getAddress(), id);

        venue.setName(venueDTO.getName());
        venue.setCity(venueDTO.getCity());
        venue.setAddress(venueDTO.getAddress());
        venue.setMaxCapacity(venueDTO.getMaxCapacity());

        return venueRepository.save(venue);
    }

    public void deleteAllVenues() {
        venueRepository.deleteAll();
    }

    public void deleteVenueById(Integer id) {
        Venue venue = getVenueById(id);
        venueRepository.delete(venue);
    }

    private void validateUniqueAddress(String address, Integer currentId) {
        Venue existing = venueRepository.findByAddressIgnoreCase(address).orElse(null);
        if (existing != null && !existing.getId().equals(currentId)) {
            throw new ValidationException("Ya hay un venue con esta direccion");
        }
    }
}
