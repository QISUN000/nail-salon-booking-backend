package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    public Professional saveProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }
}