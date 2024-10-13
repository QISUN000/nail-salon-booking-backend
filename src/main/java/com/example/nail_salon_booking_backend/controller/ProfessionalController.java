package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping
    public List<Professional> getAllProfessionals() {
        return professionalService.getAllProfessionals();
    }

    @GetMapping("/{id}")
    public Professional getProfessionalById(@PathVariable Long id) {
        return professionalService.getProfessionalById(id);
    }

    @PostMapping
    public Professional createProfessional(@RequestBody Professional professional) {
        return professionalService.saveProfessional(professional);
    }

    @PutMapping("/{id}")
    public Professional updateProfessional(@PathVariable Long id, @RequestBody Professional professional) {
        professional.setId(id);
        return professionalService.saveProfessional(professional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }
}