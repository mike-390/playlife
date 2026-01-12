package com.playlife.backend.modules.venue.dto;

import java.util.UUID;

public record VenueResponse(
        UUID id,
        String name,
        String email,
        String address,
        String phoneNumber,
        Double latitude,
        Double longitude
) {}