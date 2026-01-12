package com.playlife.backend.modules.venue.service.impl;

import com.playlife.backend.modules.venue.dto.VenueRequest;
import com.playlife.backend.modules.venue.dto.VenueResponse;
import com.playlife.backend.modules.venue.entity.Venue;
import com.playlife.backend.modules.venue.mapper.VenueMapper;
import com.playlife.backend.modules.venue.repository.VenueRepository;
import com.playlife.backend.modules.venue.service.VenueService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Override
    @Transactional
    public VenueResponse createVenue(VenueRequest request){
        if (venueRepository.existsByEmail(request.email())){
            throw new RuntimeException("Venue with this email already exists");
        }

        Venue venue = venueMapper.toEnity(request);
        Venue saved = venueRepository.save(venue);

        return venueMapper.toResponse(saved);
    }

    @Override
    public List<VenueResponse> getAllVenues() {
        return venueRepository.findAll()
                .stream()
                .map(venue -> venueMapper.toResponse(venue))
                .toList();
    }
}
