package com.playlife.backend.modules.venue.service.impl;

import com.playlife.backend.modules.venue.dto.CourtImageResponse;
import com.playlife.backend.modules.venue.entity.Court;
import com.playlife.backend.modules.venue.entity.CourtImage;
import com.playlife.backend.modules.venue.mapper.CourtImageMapper;
import com.playlife.backend.modules.venue.repository.CourtImageRepository;
import com.playlife.backend.modules.venue.repository.CourtRepository;
import com.playlife.backend.modules.venue.service.CourtImagesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
public class CourtImageServiceImpl implements CourtImagesService {

    private final CourtRepository courtRepository;
    private final CourtImageRepository courtImageRepository;
    private final CourtImageMapper courtImageMapper;
    private final String UPLOAD_DIR = "uploads/courts/";

    @Override
    @Transactional
    public CourtImageResponse uploadImage(UUID courtId, MultipartFile file, boolean isMain) {

        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Court not found with id" + courtId));

        String originalFilename = file.getOriginalFilename();
        String extension = (originalFilename != null && originalFilename.contains("."))
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";

        String uniqueFilename = UUID.randomUUID().toString() + extension;

        try{
            Path uploadPath = Paths.get(UPLOAD_DIR );
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
            String fileUrl = "/uploads/courts/" + uniqueFilename;

            CourtImage image = CourtImage.builder()
                    .court(court)
                    .imageUrl(fileUrl)
                    .isMain(isMain)
                    .build();

            CourtImage savedImage = courtImageRepository.save(image);

            return courtImageMapper.toResponse(savedImage);
        } catch (IOException e){
            throw new RuntimeException("Could not store file" + uniqueFilename, e);
        }
    }

    public List<CourtImageResponse> getImages(UUID courtId){
        return courtImageRepository.findAllByCourtId(courtId)
                .stream()
                .map(courtImage -> courtImageMapper.toResponse(courtImage))
                .toList();
    }
}
