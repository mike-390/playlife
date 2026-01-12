package com.playlife.backend.modules.venue.repository;

import com.playlife.backend.modules.venue.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {
    boolean existsByEmail(String email);
}
