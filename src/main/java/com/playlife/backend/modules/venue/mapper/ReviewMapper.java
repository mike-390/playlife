package com.playlife.backend.modules.venue.mapper;

import com.playlife.backend.modules.venue.dto.ReviewRequest;
import com.playlife.backend.modules.venue.dto.ReviewResponse;
import com.playlife.backend.modules.venue.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="createdAt",  ignore = true)
    @Mapping(target="user",  ignore = true)
    @Mapping(target="venue", ignore = true)
    Review toEntity(ReviewRequest request);

    @Mapping(target ="user", expression = "java(review.getUser().getFirstName() + ' ' + review.getUser.getLastName())")
    ReviewResponse toResponse(Review review);
}
