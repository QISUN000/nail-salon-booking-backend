package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.Booking;
import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);
    List<Booking> findByProfessional(Professional professional);
    List<Booking> findByProfessionalAndStartTimeBetween(Professional professional, LocalDateTime start, LocalDateTime end);
}