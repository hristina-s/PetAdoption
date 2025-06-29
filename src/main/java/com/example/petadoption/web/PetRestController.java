package com.example.petadoption.web;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.dto.PetDto;
import com.example.petadoption.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pets")
public class PetRestController {

    private final PetService petService;

    public PetRestController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping
    public List<PetDto> findAllPets() {
        return this.petService.findAllPets();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PetDto> findPetById(@PathVariable Long id) {
        return this.petService.findPetById(id)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/shelter/{shelterId}")
    public List<PetDto> findPetsByShelter(@PathVariable Long shelterId) {
        return this.petService.findPetsByShelter(shelterId);
    }

    @GetMapping("type/{type}")
    public List<PetDto> findPetsByType(@PathVariable PetType type) {
        return this.petService.findPetsByType(type);
    }

    @GetMapping("/gender/{gender}")
    public List<PetDto> findPetsByGender(@PathVariable Gender gender) {
        return this.petService.findPetsByGender(gender);
    }

    @PostMapping("/add")
    public ResponseEntity<PetDto> create(@RequestParam String name,
                                   @RequestParam String age,
                                   @RequestParam Gender gender,
                                   @RequestParam PetType type,
                                   @RequestParam byte[] image,
                                   @RequestParam Shelter shelter) {
        return this.petService.create(name, age, gender, type, image, shelter)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PetDto> update(@PathVariable Long id,
                                         @RequestParam String name,
                                         @RequestParam String age,
                                         @RequestParam Gender gender,
                                         @RequestParam PetType type,
                                         @RequestParam byte[] image,
                                         @RequestParam Shelter shelter) {
        return this.petService.update(id, name, age, gender, type, image, shelter)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable Long id) {
        this.petService.deletePet(id);
    }
}
