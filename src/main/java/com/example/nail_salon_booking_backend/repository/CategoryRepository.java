package com.example.nail_salon_booking_backend.repository;

import com.example.nail_salon_booking_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
