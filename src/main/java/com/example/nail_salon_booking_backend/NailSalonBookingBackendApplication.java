package com.example.nail_salon_booking_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import com.example.nail_salon_booking_backend.security.SecurityConfig;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class NailSalonBookingBackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NailSalonBookingBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(NailSalonBookingBackendApplication.class, args);
	}
}