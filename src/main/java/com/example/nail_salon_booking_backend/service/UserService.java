package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.User;
import com.example.nail_salon_booking_backend.repository.UserRepository;
import com.example.nail_salon_booking_backend.payload.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setProvider(User.AuthProvider.LOCAL);
        user.setRole(User.UserRole.CUSTOMER);
        return userRepository.save(user);
    }

    public String authenticateGoogleUser(String idToken) {
        // Implement Google authentication logic here
        throw new UnsupportedOperationException("Google authentication not implemented yet");
    }

    public List<User> getUsersByRole(User.UserRole role) {
        return userRepository.findByRole(role);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}