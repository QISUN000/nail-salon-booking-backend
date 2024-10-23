package com.example.nail_salon_booking_backend.service;

import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Optional<Professional> getProfessionalById(Long id) {
        return professionalRepository.findById(id);
    }

    public Professional addProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public Optional<Professional> updateProfessional(Long id, Professional updatedProfessional) {
        return professionalRepository.findById(id)
                .map(professional -> {
                    professional.setName(updatedProfessional.getName());
                    professional.setImageUrl(updatedProfessional.getImageUrl());
                    professional.setInstagramHandle(updatedProfessional.getInstagramHandle());
                    professional.setPrice(updatedProfessional.getPrice());
                    professional.setRole(updatedProfessional.getRole());
//                    professional.setAvailable(updatedProfessional.isAvailable());
                    return professionalRepository.save(professional);
                });
    }

    public boolean deleteProfessional(Long id) {
        return professionalRepository.findById(id)
                .map(professional -> {
                    professionalRepository.delete(professional);
                    return true;
                })
                .orElse(false);
    }

    public List<Professional> searchProfessionalsByName(String name) {
        return professionalRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Professional> getProfessionalsByMaxPrice(Double maxPrice) {
        return professionalRepository.findByPriceLessThanEqual(maxPrice);
    }

//    public List<Professional> getAvailableProfessionals() {
//        return professionalRepository.findByAvailableTrue();
//    }

//    public void toggleProfessionalAvailability(Long id, boolean available) {
//        professionalRepository.findById(id).ifPresent(professional -> {
//            professional.setAvailable(available);
//            professionalRepository.save(professional);
//        });
//    }
}