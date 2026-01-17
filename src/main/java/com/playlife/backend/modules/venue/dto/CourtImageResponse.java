package com.playlife.backend.modules.venue.dto;

import java.util.UUID;

public record CourtImageResponse (
   UUID id,
   String imageUrl,
   boolean isCover
){ }
