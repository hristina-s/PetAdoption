package com.example.petadoption.service;

import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.dto.ShelterDto;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    List<ShelterDto> findAllShelters();
    Optional<ShelterDto> findShelterById(Long id);
    Optional<ShelterDto> create(String name, String location, String capacity);
    Optional<ShelterDto> update(Long id, String name, String location, String capacity);
    void deleteShelter(Long id);
}
