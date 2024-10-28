package com.example.nail_salon_booking_backend.controller;

import com.example.nail_salon_booking_backend.model.Professional;
import com.example.nail_salon_booking_backend.service.ProfessionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.nail_salon_booking_backend.security.UserPrincipal;

import java.net.URI;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping
    public ResponseEntity<List<Professional>> getAllProfessionals() {
        return ResponseEntity.ok(professionalService.getAllProfessionals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> getProfessionalById(@PathVariable Long id) {
        try {
            Professional professional = professionalService.getProfessionalById(id);
            return ResponseEntity.ok(professional);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Professional> addProfessional(@RequestBody Professional professional) {
        Professional newProfessional = professionalService.addProfessional(professional);
        return ResponseEntity.created(URI.create("/api/professionals/" + newProfessional.getId()))
                .body(newProfessional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professional> updateProfessional(@PathVariable Long id, @RequestBody Professional professional) {
        return professionalService.updateProfessional(id, professional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable Long id) {
        if (professionalService.deleteProfessional(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Professional>> searchProfessionals(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) Double maxPrice) {
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(professionalService.searchProfessionalsByName(name));
        } else if (maxPrice != null) {
            return ResponseEntity.ok(professionalService.getProfessionalsByMaxPrice(maxPrice));
        } else {
            return ResponseEntity.ok(professionalService.getAllProfessionals());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Professional> getProfessionalByCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            Professional professional = professionalService.getProfessionalByUserId(userPrincipal.getId());
            return ResponseEntity.ok(professional);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/available")
//    public ResponseEntity<List<Professional>> getAvailableProfessionals() {
//        return ResponseEntity.ok(professionalService.getAvailableProfessionals());
//    }
//
//    @PatchMapping("/{id}/availability")
//    public ResponseEntity<Void> toggleProfessionalAvailability(@PathVariable Long id, @RequestParam boolean available) {
//        professionalService.toggleProfessionalAvailability(id, available);
//        return ResponseEntity.ok().build();
//    }
}