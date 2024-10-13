package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}