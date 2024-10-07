package com.example.nail_salon_booking_backend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class NailService {  // Renamed from Service to NailService
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String duration;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // No-args constructor
    public NailService() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


