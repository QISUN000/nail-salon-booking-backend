package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.Booking;
import com.example.nail_salon_booking_backend.model.User;
import com.example.nail_salon_booking_backend.payload.BookingRequest;
import com.example.nail_salon_booking_backend.payload.ApiResponse;
import com.example.nail_salon_booking_backend.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @Operation(summary = "Create a new booking", description = "Creates a new booking for the authenticated user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest, @AuthenticationPrincipal User user) {
        try {
            Booking booking = new Booking();
            booking.setCustomer(user);
            booking.setProfessional(bookingRequest.getProfessional());
            booking.setServices(bookingRequest.getServices());
            booking.setStartTime(bookingRequest.getStartTime());

            Booking createdBooking = bookingService.createBooking(booking, user);
            return ResponseEntity.created(URI.create("/api/bookings/" + createdBooking.getId()))
                    .body(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a booking by ID", description = "Retrieves a booking by its ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved booking"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Booking not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Booking> getBooking(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return bookingService.getBookingById(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-bookings")
    @Operation(summary = "Get user's bookings", description = "Retrieves all bookings for the authenticated user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved bookings"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<Booking>> getMyBookings(@AuthenticationPrincipal User user) {
        List<Booking> bookings = bookingService.getBookingsForCustomer(user, user);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a booking", description = "Updates an existing booking")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest, @AuthenticationPrincipal User user) {
        Booking updatedBooking = new Booking();
        updatedBooking.setServices(bookingRequest.getServices());
        updatedBooking.setStartTime(bookingRequest.getStartTime());
        updatedBooking.setProfessional(bookingRequest.getProfessional());

        Booking result = bookingService.updateBooking(id, updatedBooking, user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id, @AuthenticationPrincipal User user) {
        bookingService.cancelBooking(id, user);
        return ResponseEntity.ok().build();
    }
}