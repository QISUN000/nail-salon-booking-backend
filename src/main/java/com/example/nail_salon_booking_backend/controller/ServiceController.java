package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.NailService;
import com.example.nail_salon_booking_backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<NailService> getAllServices() {
        return serviceService.getAllServices();
    }

    // Other controller methods
}