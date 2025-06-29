package com.example.petadoption.web;

import com.example.petadoption.model.dto.ShelterDto;
import com.example.petadoption.service.PetService;
import com.example.petadoption.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelters")
public class ShelterRestController {

    private final ShelterService shelterService;

    public ShelterRestController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping
    public List<ShelterDto> findAllShelters() {
        return this.shelterService.findAllShelters();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ShelterDto> findShelterById(@PathVariable Long id) {
        return this.shelterService.findShelterById(id)
                .map(shelter -> ResponseEntity.ok().body(shelter))
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Optional<ShelterDto> create(@RequestParam String name,
                                       @RequestParam String location,
                                       @RequestParam String capacity) {
        return this.shelterService.create(name, location, capacity);
    }

    @PutMapping("/edit/{id}")
    public Optional<ShelterDto> update(@PathVariable Long id,
                                       @RequestParam String name,
                                       @RequestParam String location,
                                       @RequestParam String capacity) {
        return this.shelterService.update(id, name, location, capacity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShelter(@PathVariable Long id) {
        this.shelterService.deleteShelter(id);
    }
}
