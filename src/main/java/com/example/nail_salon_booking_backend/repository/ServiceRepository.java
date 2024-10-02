package com.example.nail_salon_booking_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ServiceRepository extends JpaRepository <Service, Long> {
}
