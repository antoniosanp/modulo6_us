package com.example.eventify.repository;

import com.example.eventify.model.Venue;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

    public List<Venue> findByName(String name);
    public Venue findByAddress(String address);

}
