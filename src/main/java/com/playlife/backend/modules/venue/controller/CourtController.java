package com.playlife.backend.modules.venue.controller;

import com.playlife.backend.modules.venue.dto.CourtRequest;
import com.playlife.backend.modules.venue.dto.CourtResponse;
import com.playlife.backend.modules.venue.entity.Court;
import com.playlife.backend.modules.venue.service.CourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/venues/{venueId}/courts")
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @PostMapping
    public ResponseEntity<CourtResponse> createCourt(@PathVariable UUID venueId, @RequestBody @Valid CourtRequest request) {
        return ResponseEntity.ok(courtService.createCourt(venueId, request));
    }

    @GetMapping
    public ResponseEntity<List<CourtResponse>> getCourtsByVenue(@PathVariable UUID venueId){
        return ResponseEntity.ok(courtService.getCourtsByVenue(venueId));
    }

    @PutMapping("/{courtId}")
    public ResponseEntity<CourtResponse> updateCourt(@PathVariable UUID venueId, @PathVariable UUID courtId,@RequestBody @Valid CourtRequest request){
        return ResponseEntity.ok(courtService.updateCourt(venueId, courtId, request));
    }

    @DeleteMapping("/{courtId}")
    public ResponseEntity<Void> deleteCourt(@PathVariable UUID venueId, @PathVariable UUID courtId){
        courtService.deleteCourt(venueId, courtId);

        return ResponseEntity.noContent().build();
    }
}
