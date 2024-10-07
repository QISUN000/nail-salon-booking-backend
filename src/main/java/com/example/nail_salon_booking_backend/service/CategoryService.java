package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.Category;
import com.example.nail_salon_booking_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}