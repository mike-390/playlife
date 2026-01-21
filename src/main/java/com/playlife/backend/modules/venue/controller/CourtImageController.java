package com.playlife.backend.modules.venue.controller;

import com.playlife.backend.modules.venue.dto.CourtImageResponse;
import com.playlife.backend.modules.venue.repository.CourtImageRepository;
import com.playlife.backend.modules.venue.repository.VenueImageRepository;
import com.playlife.backend.modules.venue.service.CourtImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courts/{courtId}/images")
@RequiredArgsConstructor
public class CourtImageController {
    private final CourtImagesService courtImagesService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourtImageResponse> uploadImage(@PathVariable UUID courtId, @RequestParam("file") MultipartFile file,
                                                          @RequestParam(value = "isMain", defaultValue = "false") boolean isMain) {
        return ResponseEntity.ok(courtImagesService.uploadImage(courtId, file, isMain));
    }

    @GetMapping
    public ResponseEntity<List<CourtImageResponse>> getImage(@PathVariable UUID courtId) {
        return ResponseEntity.ok(courtImagesService.getImages(courtId));
    }
}
