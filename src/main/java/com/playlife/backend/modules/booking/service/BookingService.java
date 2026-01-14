package com.playlife.backend.modules.booking.service;

import com.playlife.backend.modules.booking.dto.BookingRequest;
import com.playlife.backend.modules.booking.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request, String userEmail);
    List<BookingResponse> getAllBookings(String userEmail);
}
