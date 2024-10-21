package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendBookingConfirmation(Booking booking) {
        // In a real application, this would send an email or push notification
        System.out.println("Booking confirmation sent for booking ID: " + booking.getId());
    }

    public void sendBookingUpdateNotification(Booking booking) {
        // In a real application, this would send an email or push notification
        System.out.println("Booking update notification sent for booking ID: " + booking.getId());
    }

    public void sendBookingCancellationNotification(Booking booking) {
        // In a real application, this would send an email or push notification
        System.out.println("Booking cancellation notification sent for booking ID: " + booking.getId());
    }

    public void sendBookingReminder(Booking booking) {
        // In a real application, this would send an email or push notification
        System.out.println("Booking reminder sent for booking ID: " + booking.getId());
    }
}