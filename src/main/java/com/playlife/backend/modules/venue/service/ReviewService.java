package com.playlife.backend.modules.venue.service;

import com.playlife.backend.modules.venue.dto.ReviewRequest;
import com.playlife.backend.modules.venue.dto.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponse addReview(UUID venueId, String userEmail, ReviewRequest request);
    List<ReviewResponse> getVenueReviews(UUID venueId);
}
