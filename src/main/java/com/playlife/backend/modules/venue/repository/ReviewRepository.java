package com.playlife.backend.modules.venue.repository;

import com.playlife.backend.modules.venue.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByVenueId(UUID venueId);
}
