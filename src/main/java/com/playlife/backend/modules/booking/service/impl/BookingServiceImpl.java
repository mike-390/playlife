package com.playlife.backend.modules.booking.service.impl;

import com.playlife.backend.enums.BookingStatus;
import com.playlife.backend.modules.booking.dto.BookingRequest;
import com.playlife.backend.modules.booking.dto.BookingResponse;
import com.playlife.backend.modules.booking.entity.Booking;
import com.playlife.backend.modules.booking.mapper.BookingMapper;
import com.playlife.backend.modules.booking.repository.BookingRepository;
import com.playlife.backend.modules.booking.service.BookingService;
import com.playlife.backend.modules.user.Entity.User;
import com.playlife.backend.modules.user.repository.UserRepository;
import com.playlife.backend.modules.venue.entity.Court;
import com.playlife.backend.modules.venue.repository.CourtRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User hot found"));

        Court court = courtRepository.findById(request.courtId())
                .orElseThrow(() -> new RuntimeException("Court not found"));

        boolean overlap = bookingRepository.existOverlappingBooking(
                court.getId(), request.startTime(), request.endTime()
        );

        if (overlap) {
            throw new RuntimeException("Court is already booked for this time slot!");
        }

        double hours = Duration.between(request.startTime(), request.endTime()).toMinutes() / 60.0;
        BigDecimal totalPrice = court.getPricePerHour().multiply(BigDecimal.valueOf(hours));

        Booking booking = bookingMapper.toEntity(request);
        booking.setUser(user);
        booking.setCourt(court);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    public List<BookingResponse> getAllBookings(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User hot found"));

        return bookingRepository.findAllByUserId(user.getId())
                .stream()
                .map(booking -> bookingMapper.toResponse(booking))
                .toList();
    }

}
