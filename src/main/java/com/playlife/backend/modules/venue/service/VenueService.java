package com.playlife.backend.modules.venue.service;

import com.playlife.backend.modules.venue.dto.VenueRequest;
import com.playlife.backend.modules.venue.dto.VenueResponse;
import com.playlife.backend.modules.venue.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VenueService {
    VenueResponse createVenue(VenueRequest request);
    List<VenueResponse> getAllVenues();
}
