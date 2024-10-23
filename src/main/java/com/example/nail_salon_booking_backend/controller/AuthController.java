package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.User;
import com.example.nail_salon_booking_backend.payload.*;
import com.example.nail_salon_booking_backend.repository.UserRepository;
import com.example.nail_salon_booking_backend.security.JwtTokenProvider;
import com.example.nail_salon_booking_backend.security.UserPrincipal;
import com.example.nail_salon_booking_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestBody EmailCheckRequest request) {
        boolean exists = userService.emailExists(request.getEmail());
        return ResponseEntity.ok(new EmailCheckResponse(exists));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            // Log incoming request
            System.out.println("Received registration request for email: " + request.getEmail());

            // Validate request
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Email is required"));
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Password is required"));
            }
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Name is required"));
            }

            if (userService.emailExists(request.getEmail())) {
                System.out.println("Registration failed: Email already exists - " + request.getEmail());
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Email is already taken!"));
            }

            User user = userService.registerUser(request);
            System.out.println("User registered successfully: " + user.getEmail());

            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
        } catch (Exception e) {
            System.err.println("Registration error: " + e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user.getRole()));
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest request) {
        String token = userService.authenticateGoogleUser(request.getIdToken());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token, User.UserRole.CUSTOMER));
    }
}