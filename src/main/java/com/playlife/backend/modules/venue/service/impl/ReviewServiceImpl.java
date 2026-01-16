package com.playlife.backend.modules.venue.service.impl;

import com.playlife.backend.modules.user.Entity.User;
import com.playlife.backend.modules.user.repository.UserRepository;
import com.playlife.backend.modules.venue.dto.ReviewRequest;
import com.playlife.backend.modules.venue.dto.ReviewResponse;
import com.playlife.backend.modules.venue.entity.Review;
import com.playlife.backend.modules.venue.entity.Venue;
import com.playlife.backend.modules.venue.mapper.ReviewMapper;
import com.playlife.backend.modules.venue.repository.ReviewRepository;
import com.playlife.backend.modules.venue.repository.VenueRepository;
import com.playlife.backend.modules.venue.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReviewResponse addReview(UUID venueId, String userEmail, ReviewRequest request){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        Review review = reviewMapper.toEntity(request);
        review.setVenue(venue);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toResponse(savedReview);
    }

    @Override
    public List<ReviewResponse> getVenueReviews(UUID venueId){
        return reviewRepository.findAllByVenueId(venueId)
                .stream()
                .map(review -> reviewMapper.toResponse(review))
                .toList();
    }
}
