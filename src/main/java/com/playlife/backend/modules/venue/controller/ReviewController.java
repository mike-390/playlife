package com.playlife.backend.modules.venue.controller;

import com.playlife.backend.modules.venue.dto.ReviewRequest;
import com.playlife.backend.modules.venue.dto.ReviewResponse;
import com.playlife.backend.modules.venue.entity.Venue;
import com.playlife.backend.modules.venue.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/venues/{venueId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@PathVariable UUID venueId, Authentication authentication, @RequestBody @Valid ReviewRequest request){
        return ResponseEntity.ok(reviewService.addReview(venueId,authentication.getName(),request));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getVenueReviews(@PathVariable UUID venueId){
        return ResponseEntity.ok(reviewService.getVenueReviews(venueId));
    }
}
