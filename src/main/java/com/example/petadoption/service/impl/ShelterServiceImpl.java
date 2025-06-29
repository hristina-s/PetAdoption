package com.example.petadoption.service.impl;

import com.example.petadoption.exceptions.ShelterNotFoundException;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.dto.PetDto;
import com.example.petadoption.model.dto.ShelterDto;
import com.example.petadoption.repository.ShelterRepository;
import com.example.petadoption.service.ShelterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }
    private ShelterDto toDto(Shelter shelter) {
        return new ShelterDto(
                shelter.getName(),
                shelter.getCapacity(),
                shelter.getCapacity()
        );
    }

    @Override
    public List<ShelterDto> findAllShelters() {
        return this.shelterRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ShelterDto> findShelterById(Long id) {
        return this.shelterRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public Optional<ShelterDto> create(String name, String location, String capacity) {
        Shelter shelter = new Shelter(name, location, capacity);
        return Optional.of(this.shelterRepository.save(shelter))
                .map(this::toDto);
    }

    @Override
    public Optional<ShelterDto> update(Long id, String name, String location, String capacity) {
        Shelter shelter = this.shelterRepository.findById(id).orElseThrow(() -> new ShelterNotFoundException(id));
        shelter.setName(name);
        shelter.setLocation(location);
        shelter.setCapacity(capacity);
        return Optional.of(this.shelterRepository.save(shelter))
                .map(this::toDto);
    }

    @Override
    public void deleteShelter(Long id) {
        this.shelterRepository.deleteById(id);
    }
}
