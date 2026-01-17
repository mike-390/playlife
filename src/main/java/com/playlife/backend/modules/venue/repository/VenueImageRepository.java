package com.playlife.backend.modules.venue.repository;

import com.playlife.backend.modules.venue.entity.VenueImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VenueImageRepository extends JpaRepository<VenueImage, UUID> {
    List<VenueImage> findAllByVenueId(UUID venueId);
}
