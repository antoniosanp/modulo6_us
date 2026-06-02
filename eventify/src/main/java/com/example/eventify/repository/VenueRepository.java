package com.example.eventify.repository;

import com.example.eventify.model.Venue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

    Optional<Venue> findByNameIgnoreCase(String name);

    Optional<Venue> findByAddressIgnoreCase(String address);
}
