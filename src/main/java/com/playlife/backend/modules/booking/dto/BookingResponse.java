package com.playlife.backend.modules.booking.dto;

import com.playlife.backend.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponse (
        UUID id,
        String venueName,
        String courtName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal totalPrice,
        BookingStatus status,
        LocalDateTime createdAt
) { }
