package com.example.petadoption.service.impl;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import com.example.petadoption.exceptions.PetNotFoundException;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.dto.PetDto;
import com.example.petadoption.repository.PetRepository;
import com.example.petadoption.repository.ShelterRepository;
import com.example.petadoption.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    private PetDto toDto(Pet pet) {
        return new PetDto(
                pet.getName(),
                pet.getAge(),
                pet.getGender(),
                pet.getType(),
                pet.getImage()
        );
    }

    @Override
    public List<PetDto> findAllPets() {
        return this.petRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PetDto> findPetById(Long id) {
        return this.petRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public List<PetDto> findPetsByShelter(Long shelterId) {
        return this.petRepository.findPetsByShelterId(shelterId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findPetsByType(PetType type) {
        return this.petRepository.findPetByType(type)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findPetsByGender(Gender gender) {
        return this.petRepository.findPetsByGender(gender)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PetDto> create(String name, String age, Gender gender, PetType type, byte[] image, Shelter shelter) {
        Pet pet = new Pet(name, age, gender, type, image, shelter);
        return Optional.of(petRepository.save(pet))
                .map(this::toDto);
    }

    @Override
    public Optional<PetDto> update(Long id, String name, String age, Gender gender, PetType type, byte[] image, Shelter shelter) {
        Pet pet = this.petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
        pet.setName(name);
        pet.setAge(age);
        pet.setGender(gender);
        pet.setType(type);
        pet.setImage(image);
        pet.setShelter(shelter);
        return Optional.of(petRepository.save(pet))
                .map(this::toDto);
    }

    @Override
    public void deletePet(Long id) {
        this.petRepository.deleteById(id);
    }
}
