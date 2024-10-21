package com.example.nail_salon_booking_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.nail_salon_booking_backend.security.SecurityConfig;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)

public class NailSalonBookingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NailSalonBookingBackendApplication.class, args);
	}

}
