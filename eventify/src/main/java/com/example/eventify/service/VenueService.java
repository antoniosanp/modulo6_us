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

    //GET---------------------------------------------
    public List<Venue> getAll(){
        return venueRepository.findAll();
    }

    public Venue getById(int id){
        return venueRepository.findById(id).orElseThrow( ()-> new RuntimeException("está mal la id"));
    }

    public List<Venue> getByName(String name){
        return venueRepository.findByName(name);
    }


    //POST---------------------------------------------

    public Venue createEvent(VenueDTO venueDTO){

        Venue ve = venueRepository.findByAddress(venueDTO.getAddress());
        if (ve != null) {throw new RuntimeException("Ya hay un destino con esta dirección");}
        Venue v = new Venue();
        v.setName(venueDTO.getName());
        v.setAddress((venueDTO.getAddress()));
        v.setMaxCapacity(venueDTO.getMaxCapacity());

        venueRepository.save(v);
        return v;
    }

    //PATCH-------------------------------------------

    public Venue patchEvent(){
        return null;
    }

    //PUT--------------------------------------------

    public Venue putEvent(){
        return null;
    }

    //DELETE

    public Venue deletEvent(){
        return null;
    }

}
