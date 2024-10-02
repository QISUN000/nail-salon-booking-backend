package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.NailService;
import com.example.nail_salon_booking_backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<NailService> getAllServices() {
        return serviceRepository.findAll();
    }

    // Other service methods
}