package com.playlife.backend.modules.venue.service;

import com.playlife.backend.modules.venue.dto.CourtRequest;
import com.playlife.backend.modules.venue.dto.CourtResponse;
import com.playlife.backend.modules.venue.entity.Court;

import java.util.List;
import java.util.UUID;

public interface CourtService {
    CourtResponse createCourt(UUID venueId, CourtRequest courtRequest);
    List<CourtResponse> getCourtsByVenue(UUID venueId);
    CourtResponse updateCourt(UUID venueId, UUID courtId,CourtRequest courtRequest);
    void deleteCourt(UUID venueId, UUID courtId);
}
