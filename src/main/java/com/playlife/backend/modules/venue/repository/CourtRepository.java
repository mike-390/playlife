package com.playlife.backend.modules.venue.repository;

import com.playlife.backend.modules.venue.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourtRepository extends JpaRepository<Court, UUID> {
    List<Court> findAllByVenueId(UUID venueId);
}
