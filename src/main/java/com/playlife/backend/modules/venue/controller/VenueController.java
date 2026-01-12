package com.playlife.backend.modules.venue.controller;

import com.playlife.backend.modules.venue.dto.VenueRequest;
import com.playlife.backend.modules.venue.dto.VenueResponse;
import com.playlife.backend.modules.venue.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(@RequestBody @Valid VenueRequest request) {
        return ResponseEntity.ok(venueService.createVenue(request));
    }

    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }
}
