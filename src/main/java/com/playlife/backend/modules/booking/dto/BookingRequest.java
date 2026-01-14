package com.playlife.backend.modules.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingRequest (
    @NotNull(message = "Court ID is required")
    UUID courtId,

    @NotNull(message = "Start time is required")
    @Future(message = "Booking time is required")
    LocalDateTime startTime,

    @NotNull(message = "End time is required")
    @Future(message = "Booking time must be in the future")
    LocalDateTime endTime
) { }
