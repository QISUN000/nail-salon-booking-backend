package com.example.nail_salon_booking_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // Allows handling exceptions across the whole application
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)  // Handles all exceptions
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }
}