package com.playlife.backend.modules.booking.repository;

import com.playlife.backend.modules.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("SELECT COUNT(b) > 0 FROM Booking b " +
            "WHERE b.court.id = :courtId " +
            "AND b.status != 'CANCELLED' " +
            "AND ((:startTime < b.endTime) AND (:endTime > b.startTime))"
    )
    boolean existOverlappingBooking(@Param("courtId") UUID courtId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    List<Booking> findAllByUserId(UUID userId);
    List<Booking> findAllByCourtId(UUID courtId);
}
