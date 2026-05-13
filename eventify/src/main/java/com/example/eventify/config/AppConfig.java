//package com.example.eventify.config;
//
//import com.example.eventify.model.Event;
//import com.example.eventify.model.Venue;
//import com.example.eventify.repository.EventRepository;
//import com.example.eventify.repository.VenueRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public CommandLineRunner loadData(
//            @Value("${app.seed.enabled:true}") boolean seedEnabled,
//            EventRepository eventRepository,
//            VenueRepository venueRepository) {
//
//        return args -> {
//            if (!seedEnabled) {
//                return;
//            }
//
//            Venue venue1 = new Venue();
//            venue1.setId(1);
//            venue1.setName("Medellin Arena");
//            venue1.setAddress("Calle 10 #45-20");
//            venue1.setMaxCapacity(15000);
//
//            Venue venue2 = new Venue();
//            venue2.setId(2);
//            venue2.setName("Teatro Central");
//            venue2.setAddress("Carrera 70 #15-40");
//            venue2.setMaxCapacity(800);
//
//            venueRepository.addVenue(venue1);
//            venueRepository.addVenue(venue2);
//
//            Event event1 = new Event();
//            event1.setId(1);
//            event1.setName("Rock Concert");
//            event1.setEventDate(LocalDate.of(2026, 6, 15));
//            event1.setDescription("International rock bands live.");
//
//            Event event2 = new Event();
//            event2.setId(2);
//            event2.setName("Tech Conference");
//            event2.setEventDate(LocalDate.of(2026, 8, 10));
//            event2.setDescription("Software and AI conference.");
//
//            eventRepository.addEvent(event1);
//            eventRepository.addEvent(event2);
//        };
//    }
//}
