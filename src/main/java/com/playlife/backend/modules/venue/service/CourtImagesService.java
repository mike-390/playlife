package com.playlife.backend.modules.venue.service;

import com.playlife.backend.modules.venue.dto.CourtImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CourtImagesService {
    CourtImageResponse uploadImage(UUID courtId, MultipartFile file, boolean isMain);
    List<CourtImageResponse> getImages(UUID courtId);
}
