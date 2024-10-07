package com.example.nail_salon_booking_backend.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import org.apache.catalina.Service;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<NailService> services;

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

    public List<NailService> getServices() {
        return services;
    }

    public void setServices(List<NailService> services) {
        this.services = services;
    }
}
