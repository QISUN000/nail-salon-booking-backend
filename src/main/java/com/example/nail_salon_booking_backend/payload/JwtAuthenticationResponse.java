package com.example.nail_salon_booking_backend.payload;

import com.example.nail_salon_booking_backend.model.User;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User.UserRole role;

    public JwtAuthenticationResponse(String accessToken, User.UserRole role) {
        this.accessToken = accessToken;
        this.role = role;
    }

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public User.UserRole getRole() {
        return role;
    }

    public void setRole(User.UserRole role) {
        this.role = role;
    }
}