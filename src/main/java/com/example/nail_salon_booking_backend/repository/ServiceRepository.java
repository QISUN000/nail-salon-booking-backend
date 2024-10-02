package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.NailService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository <NailService, Long> {
}
