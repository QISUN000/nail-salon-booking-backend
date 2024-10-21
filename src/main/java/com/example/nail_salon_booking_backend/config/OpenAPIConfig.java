package com.example.nail_salon_booking_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nail Salon Booking API")
                        .version("1.0")
                        .description("API for managing nail salon bookings")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")
                                .url("www.example.com")));
    }
}