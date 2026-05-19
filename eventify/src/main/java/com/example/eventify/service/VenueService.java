package com.example.eventify.service;

import com.example.eventify.dto.VenueDTO;
import com.example.eventify.exception.ResourceNotFoundException;
import com.example.eventify.exception.ValidationException;
import com.example.eventify.model.Venue;
import com.example.eventify.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VenueService {

    private final VenueRepository venueRepository;

    // GET
    public List<Venue> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        if (venues.isEmpty()) {
            throw new ResourceNotFoundException("no hay venues");
        }
        return venues;
    }

    public Venue getVenueById(int id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no hay venue con ese id"));
    }

    // POST
    public Venue addVenue(VenueDTO venueDTO) {
        Venue existing = venueRepository.findByAddress(venueDTO.getAddress());
        if (existing != null) {
            throw new ValidationException("Ya hay un venue con esta dirección");
        }

        Venue v = new Venue();
        v.setName(venueDTO.getName());
        v.setAddress(venueDTO.getAddress());
        v.setMaxCapacity(venueDTO.getMaxCapacity());
        return venueRepository.save(v);
    }

    // PATCH
    public Venue patchVenue(int id, VenueDTO venueDTO) {
        Venue v = getVenueById(id);

        if (venueDTO.getName() != null) {
            v.setName(venueDTO.getName());
        }
        if (venueDTO.getAddress() != null) {
            Venue byAddress = venueRepository.findByAddress(venueDTO.getAddress());
            if (byAddress != null && !byAddress.getId().equals(id)) {
                throw new ValidationException("Ya hay un venue con esta dirección");
            }
            v.setAddress(venueDTO.getAddress());
        }
        // maxCapacity es int. Si llega en el body con valor >= 0, lo aplicamos.
        if (venueDTO.getMaxCapacity() >= 0) {
            v.setMaxCapacity(venueDTO.getMaxCapacity());
        }

        return venueRepository.save(v);
    }

    // PUT
    public Venue putVenue(int id, VenueDTO venueDTO) {
        Venue v = getVenueById(id);

        Venue byAddress = venueRepository.findByAddress(venueDTO.getAddress());
        if (byAddress != null && !byAddress.getId().equals(id)) {
            throw new ValidationException("Ya hay un venue con esta dirección");
        }

        v.setName(venueDTO.getName());
        v.setAddress(venueDTO.getAddress());
        v.setMaxCapacity(venueDTO.getMaxCapacity());

        return venueRepository.save(v);
    }

    // DELETE
    public void deleteAllVenues() {
        venueRepository.deleteAll();
    }

    public void deleteVenueById(int id) {
        Venue v = getVenueById(id);
        venueRepository.delete(v);
    }
}


