package com.playlife.backend.modules.booking.controller;

import com.playlife.backend.modules.booking.dto.BookingRequest;
import com.playlife.backend.modules.booking.dto.BookingResponse;
import com.playlife.backend.modules.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid BookingRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(bookingService.createBooking(request, userEmail));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(bookingService.getAllBookings(userEmail));
    }
}
