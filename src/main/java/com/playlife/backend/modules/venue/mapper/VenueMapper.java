package com.playlife.backend.modules.venue.mapper;

import com.playlife.backend.modules.venue.dto.VenueRequest;
import com.playlife.backend.modules.venue.dto.VenueResponse;
import com.playlife.backend.modules.venue.entity.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courts", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Venue toEnity(VenueRequest request);

    VenueResponse toResponse(Venue venue);
}
