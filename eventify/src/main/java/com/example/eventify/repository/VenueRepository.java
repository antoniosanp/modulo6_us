package com.example.eventify.repository;

import com.example.eventify.model.Venue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

    Venue findByName(String name);

    Venue findByAddress(String address);
}
