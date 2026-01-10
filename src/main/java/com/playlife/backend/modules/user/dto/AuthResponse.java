package com.playlife.backend.modules.user.dto;

public record   AuthResponse (
        String accessToken,
        String firstName,
        String lastName,
        String email,
        String role
){ }
