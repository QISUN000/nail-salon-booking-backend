package com.example.nail_salon_booking_backend.payload;

public class EmailCheckResponse {
    private boolean exists;

    public EmailCheckResponse(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}