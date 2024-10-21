package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByUser(User user);
    List<Professional> findByNameContainingIgnoreCase(String name);
    List<Professional> findByPriceLessThanEqual(Double maxPrice);
    List<Professional> findByAvailableTrue();
}