package com.example.eventify.repository;

import com.example.eventify.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends JpaRepository<Event, Integer> {

    Event findByName(String name);

}
