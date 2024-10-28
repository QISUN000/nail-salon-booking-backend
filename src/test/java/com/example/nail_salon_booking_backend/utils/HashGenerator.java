package com.example.nail_salon_booking_backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Array of professional names
        String[] professionals = {
                "tj",
                "kai",
                "bella",
                "eileen",
                "chika",
                "daisy"
        };

        System.out.println("=== SQL INSERT Statements for Professionals ===\n");

        for (String prof : professionals) {
            String password = prof + "123";
            String hash = encoder.encode(password);

            System.out.println("-- For " + prof + " (password: " + password + ")");
            System.out.println("INSERT INTO users (");
            System.out.println("    email,");
            System.out.println("    password_hash,");
            System.out.println("    name,");
            System.out.println("    created_at,");
            System.out.println("    provider,");
            System.out.println("    provider_id,");
            System.out.println("    email_verified,");
            System.out.println("    role");
            System.out.println(") VALUES (");
            System.out.println("    '" + prof + "@salon.com',");
            System.out.println("    '" + hash + "',");
            System.out.println("    '" + prof.substring(0, 1).toUpperCase() + prof.substring(1) + "',");
            System.out.println("    CURRENT_TIMESTAMP,");
            System.out.println("    'LOCAL',");
            System.out.println("    '1',");
            System.out.println("    1,");
            System.out.println("    'PROFESSIONAL'");
            System.out.println(");\n");

            // Verify the hash works
            boolean matches = encoder.matches(password, hash);
            System.out.println("-- Verification: password matches hash = " + matches + "\n");
        }
    }
}