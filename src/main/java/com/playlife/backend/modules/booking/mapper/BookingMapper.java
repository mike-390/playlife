package com.playlife.backend.modules.booking.mapper;

import com.playlife.backend.modules.booking.dto.BookingRequest;
import com.playlife.backend.modules.booking.dto.BookingResponse;
import com.playlife.backend.modules.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "court", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "status",  ignore = true)
    @Mapping(target = "createdAt",  ignore = true)
    Booking toEntity(BookingRequest request);

    @Mapping(target = "venueName", source = "court.venue.name")
    @Mapping(target = "courtName", source = "court.name")
    BookingResponse toResponse(Booking booking);
}
