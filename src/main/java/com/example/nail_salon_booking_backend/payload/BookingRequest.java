package com.example.nail_salon_booking_backend.payload;

import com.example.nail_salon_booking_backend.model.NailService;
import com.example.nail_salon_booking_backend.model.Professional;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingRequest {
    private Professional professional;
    private Set<NailService> services;
    private LocalDateTime startTime;

    // Getters and setters


    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Set<NailService> getServices() {
        return services;
    }

    public void setServices(Set<NailService> services) {
        this.services = services;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}