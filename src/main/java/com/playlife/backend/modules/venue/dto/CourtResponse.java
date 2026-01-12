package com.playlife.backend.modules.venue.dto;

import com.playlife.backend.enums.SportType;

import java.math.BigDecimal;
import java.util.UUID;

public record CourtResponse (
   UUID id,
   String name,
   SportType sportType,
   boolean hasLights,
   BigDecimal pricePerHour
){ }
