package com.playlife.backend.modules.venue.service.impl;

import com.playlife.backend.modules.venue.dto.VenueImageResponse;
import com.playlife.backend.modules.venue.entity.Venue;
import com.playlife.backend.modules.venue.entity.VenueImage;
import com.playlife.backend.modules.venue.mapper.VenueImageMapper;
import com.playlife.backend.modules.venue.repository.VenueImageRepository;
import com.playlife.backend.modules.venue.repository.VenueRepository;
import com.playlife.backend.modules.venue.service.VenueImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VenueImageServiceImpl implements VenueImageService {

    private final VenueImageRepository venueImageRepository;
    private final VenueRepository venueRepository;
    private final VenueImageMapper venueImageMapper;
    private final String UPLOAD_DIR = "uploads/venues/";

    @Override
    @Transactional
    public VenueImageResponse uploadImage(UUID venueId, MultipartFile file, boolean isCover) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(()-> new RuntimeException("Venue not found with id:" + venueId));

        String originalFilename = file.getOriginalFilename();
        String extension = (originalFilename != null && originalFilename.contains(".")) ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";

        String uniqueFilename = UUID.randomUUID().toString() + extension;

        try{
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "/uploads/venues/" + uniqueFilename;

            VenueImage image = VenueImage.builder()
                    .venue(venue)
                    .imageUrl(fileUrl)
                    .isCover(isCover)
                    .build();

            VenueImage savedImage = venueImageRepository.save(image);

            return venueImageMapper.toResponse(savedImage);
        } catch (IOException e){
            throw new RuntimeException("Could not store file" + uniqueFilename, e);
        }
    }

    @Override
    public List<VenueImageResponse> getImages(UUID venueId) {
        return venueImageRepository.findAllByVenueId(venueId)
                .stream()
                .map(venueImage -> venueImageMapper.toResponse(venueImage))
                .toList();
    }
}