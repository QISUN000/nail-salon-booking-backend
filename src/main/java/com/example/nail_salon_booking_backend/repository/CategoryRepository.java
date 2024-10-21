package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}