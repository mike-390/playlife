package com.playlife.backend.modules.venue.mapper;

import com.playlife.backend.modules.venue.dto.VenueImageResponse;
import com.playlife.backend.modules.venue.entity.VenueImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueImageMapper{
    VenueImageResponse toResponse(VenueImage venueImage);
}
