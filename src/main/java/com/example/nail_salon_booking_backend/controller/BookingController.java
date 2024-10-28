package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.Booking;
import com.example.nail_salon_booking_backend.model.NailService;
import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.model.User;
import com.example.nail_salon_booking_backend.payload.BookingRequest;
import com.example.nail_salon_booking_backend.payload.ApiResponse;
import com.example.nail_salon_booking_backend.service.BookingService;
import com.example.nail_salon_booking_backend.service.NailServiceService;
import com.example.nail_salon_booking_backend.service.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.nail_salon_booking_backend.security.CustomUserDetailsService;
import com.example.nail_salon_booking_backend.security.UserPrincipal;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ProfessionalService professionalService;
    private final NailServiceService nailServiceService;  // Use this instead of repository directly

    public BookingController(BookingService bookingService,
                             CustomUserDetailsService customUserDetailsService,
                             ProfessionalService professionalService,
                             NailServiceService nailServiceService) {
        this.bookingService = bookingService;
        this.customUserDetailsService = customUserDetailsService;
        this.professionalService = professionalService;
        this.nailServiceService = nailServiceService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        logger.info("Received booking request: {}", bookingRequest);
        logger.info("User Principal: {}", userPrincipal);

        try {
            if (userPrincipal == null) {
                logger.error("User principal is null");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse(false, "User not authenticated"));
            }

            User user = customUserDetailsService.getUserById(userPrincipal.getId());
            if (user == null) {
                logger.error("User not found for id: {}", userPrincipal.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "User not found"));
            }

            Professional professional = professionalService.getProfessionalById(bookingRequest.getProfessionalId());
            if (professional == null) {
                logger.error("Professional not found for id: {}", bookingRequest.getProfessionalId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Professional not found"));
            }

            Set<NailService> services = nailServiceService.findAllByIdIn(bookingRequest.getServiceIds());
            if (services.isEmpty()) {
                logger.error("No services found for ids: {}", bookingRequest.getServiceIds());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "No services found"));
            }

            Booking booking = new Booking();
            booking.setCustomer(user);
            booking.setProfessional(professional);
            booking.setServices(services);
            booking.setStartTime(bookingRequest.getStartTime());

            Booking createdBooking = bookingService.createBooking(booking, user);
            return ResponseEntity.created(URI.create("/api/bookings/" + createdBooking.getId()))
                    .body(createdBooking);

        } catch (Exception e) {
            logger.error("Error creating booking", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error creating booking: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id,
                                              @AuthenticationPrincipal UserPrincipal userPrincipal) {  // Changed from User to UserPrincipal
        User user = customUserDetailsService.getUserById(userPrincipal.getId());
        return bookingService.getBookingById(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<Booking>> getMyBookings(@AuthenticationPrincipal UserPrincipal userPrincipal) {  // Changed from User to UserPrincipal
        User user = customUserDetailsService.getUserById(userPrincipal.getId());
        List<Booking> bookings = bookingService.getBookingsForCustomer(user, user);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id,
                                                 @RequestBody BookingRequest bookingRequest,
                                                 @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            User user = customUserDetailsService.getUserById(userPrincipal.getId());

            Booking updatedBooking = new Booking();

            // Get Professional by ID
            Professional professional = professionalService.getProfessionalById(bookingRequest.getProfessionalId());
            updatedBooking.setProfessional(professional);

            // Get Services by IDs
            Set<NailService> services = nailServiceService.findAllByIdIn(bookingRequest.getServiceIds());
            if (services.size() != bookingRequest.getServiceIds().size()) {
                throw new RuntimeException("One or more services not found");
            }
            updatedBooking.setServices(services);

            updatedBooking.setStartTime(bookingRequest.getStartTime());

            Booking result = bookingService.updateBooking(id, updatedBooking, user);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update booking: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id,
                                              @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = customUserDetailsService.getUserById(userPrincipal.getId());
        bookingService.cancelBooking(id, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all bookings", description = "Retrieves all bookings. Requires admin role.")
    public ResponseEntity<List<Booking>> getAllBookings(
            @RequestParam(required = false) String date) {
        try {
            List<Booking> bookings = bookingService.getAllBookings(date);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            logger.error("Error fetching all bookings", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<Booking>> getBookingsByProfessional(@PathVariable Long professionalId) {
        try {
            List<Booking> bookings = bookingService.getBookingsForProfessional(professionalId);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            logger.error("Error fetching bookings for professional: " + professionalId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}