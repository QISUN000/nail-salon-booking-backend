package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.Booking;
import com.example.nail_salon_booking_backend.model.User;
import com.example.nail_salon_booking_backend.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @AuthenticationPrincipal User user) {
        booking.setCustomer(user);
        Booking createdBooking = bookingService.createBooking(booking, user);
        return ResponseEntity.created(URI.create("/api/bookings/" + createdBooking.getId()))
                .body(createdBooking);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a booking by ID", description = "Retrieves a booking by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved booking"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Booking> getBooking(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return bookingService.getBookingById(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-bookings")
    @Operation(summary = "Get user's bookings", description = "Retrieves all bookings for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<Booking>> getMyBookings(@AuthenticationPrincipal User user) {
        List<Booking> bookings = bookingService.getBookingsForCustomer(user, user);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a booking", description = "Updates an existing booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking, @AuthenticationPrincipal User user) {
        Booking updatedBooking = bookingService.updateBooking(id, booking, user);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id, @AuthenticationPrincipal User user) {
        bookingService.cancelBooking(id, user);
        return ResponseEntity.ok().build();
    }
}