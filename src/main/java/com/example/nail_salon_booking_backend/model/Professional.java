package com.example.nail_salon_booking_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String imageUrl;
    private Double price;
    private String instagramHandle;

    // Constructors, getters, and setters

    public Professional() {}

    public Professional(String name, String role, String imageUrl, Double price, String instagramHandle) {
        this.name = name;
        this.role = role;
        this.imageUrl = imageUrl;
        this.price = price;
        this.instagramHandle = instagramHandle;
    }

    // Getters and setters for all fields

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInstagramHandle() {
        return instagramHandle;
    }

    public void setInstagramHandle(String instagramHandle) {
        this.instagramHandle = instagramHandle;
    }
}