package com.playlife.backend.modules.venue.mapper;

import com.playlife.backend.modules.venue.dto.CourtImageResponse;
import com.playlife.backend.modules.venue.entity.CourtImage;
import com.playlife.backend.modules.venue.entity.VenueImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourtImageMapper {
    CourtImageResponse toResponse(CourtImage courtImage);
}
