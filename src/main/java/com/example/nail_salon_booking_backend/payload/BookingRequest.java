package com.example.nail_salon_booking_backend.payload;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingRequest {
    private Long professionalId;  // Changed from Professional to Long
    private Set<Long> serviceIds; // Changed from Set<NailService> to Set<Long>
    private LocalDateTime startTime;

    // Getters and setters
    public Long getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(Long professionalId) {
        this.professionalId = professionalId;
    }

    public Set<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Set<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}