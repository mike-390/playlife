package com.playlife.backend.modules.venue.mapper;

import com.playlife.backend.modules.venue.dto.CourtRequest;
import com.playlife.backend.modules.venue.dto.CourtResponse;
import com.playlife.backend.modules.venue.entity.Court;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourtMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue")
    @Mapping(target = "images", ignore = true)
    Court toEntity(CourtRequest request);

    CourtResponse toResponse(Court court);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue")
    @Mapping(target = "images", ignore = true)
    void updateEntityFromRequest(CourtRequest request, @MappingTarget Court court);
}
