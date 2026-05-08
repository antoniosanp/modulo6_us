package com.example.eventify.controller;

import com.example.eventify.dto.ApiResponse;
import com.example.eventify.model.Venue;
import com.example.eventify.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Listar todos los venues", description = "Retorna el catalogo completo de venues")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues() {
        List<Venue> venues = venueService.getAllVenues();
        return ResponseEntity.ok(ApiResponse.success("Venues consultados correctamente", venues));
    }

    @Operation(summary = "Obtener venue por id", description = "Retorna un venue especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> getVenue(@PathVariable int id) {
        Venue venue = venueService.getVenueById(id);
        return ResponseEntity.ok(ApiResponse.success("Venue encontrado correctamente", venue));
    }

    @Operation(summary = "Registrar nuevo venue", description = "Crea un nuevo venue en memoria")
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> postVenue(@RequestBody Venue venue) {
        Venue savedVenue = venueService.addVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Venue registrado correctamente", savedVenue));
    }
}
