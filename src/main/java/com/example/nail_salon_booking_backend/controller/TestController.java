package com.example.nail_salon_booking_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:5173") // Add this to handle CORS
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    // Simple test without auth
    @GetMapping("/public")
    public ResponseEntity<String> publicTest() {
        logger.debug("=== Testing public endpoint ===");
        logger.info("This is an info message");
        logger.warn("This is a warning");
        logger.error("This is an error");

        System.out.println("Public endpoint test print");

        return ResponseEntity.ok("Public endpoint working!");
    }
}