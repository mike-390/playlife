package com.playlife.backend.modules.venue.dto;

import com.playlife.backend.enums.SportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CourtRequest (
  @NotBlank(message = "Court name is required")
  String name,

  @NotNull(message = "Sport type is required")
  SportType sportType,

  Boolean hasLights,

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  BigDecimal pricePerHour
) { }
