package com.playlife.backend.modules.venue.service.impl;

import com.playlife.backend.modules.venue.dto.CourtRequest;
import com.playlife.backend.modules.venue.dto.CourtResponse;
import com.playlife.backend.modules.venue.entity.Court;
import com.playlife.backend.modules.venue.entity.Venue;
import com.playlife.backend.modules.venue.mapper.CourtMapper;
import com.playlife.backend.modules.venue.repository.CourtRepository;
import com.playlife.backend.modules.venue.repository.VenueRepository;
import com.playlife.backend.modules.venue.service.CourtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final CourtMapper  courtMapper;
    private final VenueRepository venueRepository;

    @Override
    @Transactional
    public CourtResponse createCourt(UUID venueId, CourtRequest request){
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        Court court = courtMapper.toEntity(request);
        court.setVenue(venue);

        Court savedCourt = courtRepository.save(court);

        return courtMapper.toResponse(savedCourt);
    }

    @Override
    public List<CourtResponse> getCourtsByVenue(UUID venueId){
        if (venueRepository.existsById(venueId)){
            throw new RuntimeException("Venue not found");
        }

        return courtRepository.findAllByVenueId(venueId)
                .stream()
                .map(court -> courtMapper.toResponse(court))
                .toList();
    }

    @Override
    @Transactional
    public CourtResponse updateCourt(UUID venueId, UUID courtId, CourtRequest request){
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Court not found"));

        if(!court.getVenue().getId().equals(venueId)){
            throw new RuntimeException("Court does not belong to this venue");
        }

        courtMapper.updateEntityFromRequest(request, court);
        Court updatedCourt = courtRepository.save(court);

        return courtMapper.toResponse(updatedCourt);
    }

    @Override
    @Transactional
    public void deleteCourt(UUID venueId, UUID courtId) {
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Court not found"));

        if(!court.getVenue().getId().equals(venueId)){
            throw new RuntimeException("Court does not belong to this venue");
        }

        courtRepository.delete(court);
    }
}
