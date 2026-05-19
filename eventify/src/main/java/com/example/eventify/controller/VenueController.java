package com.example.eventify.controller;

import com.example.eventify.dto.ApiResponse;
import com.example.eventify.model.Venue;
import com.example.eventify.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.eventify.dto.VenueDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<ApiResponse<Venue>> postVenue(@Valid @RequestBody VenueDTO venueDTO) {
        Venue savedVenue = venueService.addVenue(venueDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Venue registrado correctamente", savedVenue));
    }

    @Operation(summary = "Actualizar parcialmente un venue", description = "Actualiza nombre/dirección/capacidad si viene en el body")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> patchVenue(@PathVariable int id, @RequestBody VenueDTO venueDTO) {
        Venue updated = venueService.patchVenue(id, venueDTO);
        return ResponseEntity.ok(ApiResponse.success("Venue actualizado correctamente", updated));
    }

    @Operation(summary = "Reemplazar un venue", description = "Actualiza completamente un venue")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> putVenue(@PathVariable int id, @Valid @RequestBody VenueDTO venueDTO) {
        Venue updated = venueService.putVenue(id, venueDTO);
        return ResponseEntity.ok(ApiResponse.success("Venue actualizado correctamente", updated));
    }

    @Operation(summary = "Eliminar todos los venues", description = "Borra el catalogo completo de venues")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllVenues() {
        venueService.deleteAllVenues();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("Venues eliminados correctamente", null));
    }

    @Operation(summary = "Eliminar un venue por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVenueById(@PathVariable int id) {
        venueService.deleteVenueById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("Venue eliminado correctamente", null));
    }
}


