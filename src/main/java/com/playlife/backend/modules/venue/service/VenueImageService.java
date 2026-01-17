package com.playlife.backend.modules.venue.service;

import com.playlife.backend.modules.venue.dto.VenueImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface VenueImageService {
    VenueImageResponse uploadImage(UUID venueId, MultipartFile file, boolean isCover);
    List<VenueImageResponse> getImages(UUID venueId);
}