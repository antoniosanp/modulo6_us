package com.example.eventify.config;

import com.example.eventify.model.Event;
import com.example.eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EventRepository eventRepository;

    @Override
    public void run(String... args) {

        // Evita insertar duplicados cada vez que inicia
        if(eventRepository.count() > 0){
            return;
        }

        List<Event> events = new ArrayList<>();

        for(int i = 1; i <= 200; i++){

            Event event = new Event();

            event.setName("Evento " + i);

            event.setDescription(
                    "Descripción del evento número " + i
            );

            event.setEventDate(
                    LocalDate.now().plusDays(i)
            );

            events.add(event);
        }

        eventRepository.saveAll(events);

        System.out.println("Eventos de prueba insertados");
    }
}