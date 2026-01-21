package com.playlife.backend.modules.venue.controller;


import com.playlife.backend.modules.venue.dto.VenueImageResponse;
import com.playlife.backend.modules.venue.dto.VenueResponse;
import com.playlife.backend.modules.venue.entity.VenueImage;
import com.playlife.backend.modules.venue.service.VenueImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/venue/{venueId}/images")
@RequiredArgsConstructor
public class VenueImageController {

    private final VenueImageService venueImageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VenueImageResponse> uploadImage(@PathVariable UUID venueId, @RequestParam("file") MultipartFile file,
                                                                @RequestParam(value = "isCover", defaultValue = "false") boolean isCover) {
        return ResponseEntity.ok(venueImageService.uploadImage(venueId,file,isCover));
    }

    @GetMapping
    public ResponseEntity<List<VenueImageResponse>> getImages(@PathVariable UUID venueId) {
        return ResponseEntity.ok(venueImageService.getImages(venueId));
    }
}
