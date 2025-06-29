package com.example.petadoption.service;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.dto.PetDto;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<PetDto> findAllPets();
    Optional<PetDto> findPetById(Long id);
    List<PetDto> findPetsByShelter(Long shelterId);
    List<PetDto> findPetsByType(PetType type);
    List<PetDto> findPetsByGender(Gender gender);
    Optional<PetDto> create(String name, String age, Gender gender, PetType type, byte[] image, Shelter shelter);
    Optional<PetDto> update(Long id, String name, String age, Gender gender, PetType type, byte[] image, Shelter shelter);
    void deletePet(Long id);
}
