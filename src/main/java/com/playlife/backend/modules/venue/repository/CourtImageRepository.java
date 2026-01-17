package com.playlife.backend.modules.venue.repository;

import com.playlife.backend.modules.venue.entity.CourtImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourtImageRepository extends JpaRepository<CourtImage, UUID> {
    List<CourtImage> findAllByCourtId(UUID courtId);
}
