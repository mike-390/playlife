package com.playlife.backend.modules.venue.dto;

import java.time.LocalDateTime;

public record ReviewResponse (

        String userName,
        Integer rating,
        String comment,
        LocalDateTime createdAt
){ }
