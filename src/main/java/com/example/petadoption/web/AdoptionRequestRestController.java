package com.example.petadoption.web;

import com.example.petadoption.enumeration.RequestStatus;
import com.example.petadoption.model.dto.AdoptionRequestDto;
import com.example.petadoption.service.AdoptionRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adoptionrequests")
public class AdoptionRequestRestController {

    private final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestRestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @GetMapping
    public List<AdoptionRequestDto> findAllAdoptionRequests() {
        return this.adoptionRequestService.findAllAdoptionRequests();
    }

    @GetMapping("/user/{username}")
    public List<AdoptionRequestDto> findAdoptionRequestsByUser(@PathVariable String username) {
        return this.adoptionRequestService.findAdoptionRequestsByUser(username);
    }

    @PostMapping("/add")
    public ResponseEntity<AdoptionRequestDto> createAdoptionRequest(@RequestParam String username,
                                                                    @RequestParam Long petId) {
        return this.adoptionRequestService.createAdoptionRequest(username, petId)
                .map(adoptionRequest -> ResponseEntity.ok().body(adoptionRequest))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AdoptionRequestDto> updateAdoptionRequest(@PathVariable Long id,
                                                              @RequestParam RequestStatus status,
                                                              @RequestParam Date approvedDate) {
        return this.adoptionRequestService.updateAdoptionRequest(id, status, approvedDate)
                .map(adoptionRequest -> ResponseEntity.ok().body(adoptionRequest))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAdoptionRequest(@PathVariable Long id) {
        this.adoptionRequestService.deleteAdoptionRequest(id);
    }
}
