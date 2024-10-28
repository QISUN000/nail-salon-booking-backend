package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.NailService;
import com.example.nail_salon_booking_backend.repository.NailServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class NailServiceService {
    private final NailServiceRepository nailServiceRepository;

    public NailServiceService(NailServiceRepository nailServiceRepository) {
        this.nailServiceRepository = nailServiceRepository;
    }

    @Transactional(readOnly = true)
    public NailService findById(Long id) {
        return nailServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Set<NailService> findAllByIdIn(Set<Long> ids) {
        return new HashSet<>(nailServiceRepository.findAllById(ids));
    }
}