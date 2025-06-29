package com.example.petadoption.service.impl;

import com.example.petadoption.enumeration.RequestStatus;
import com.example.petadoption.exceptions.PetNotFoundException;
import com.example.petadoption.exceptions.RequestNotFoundException;
import com.example.petadoption.exceptions.UserNotFoundException;
import com.example.petadoption.model.AdoptionRequest;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.User;
import com.example.petadoption.model.dto.AdoptionRequestDto;
import com.example.petadoption.repository.AdoptionRequestRepository;
import com.example.petadoption.repository.PetRepository;
import com.example.petadoption.repository.UserRepository;
import com.example.petadoption.service.AdoptionRequestService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository, UserRepository userRepository, PetRepository petRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    private AdoptionRequestDto toDto(AdoptionRequest adoptionRequest) {
        return new AdoptionRequestDto(
                adoptionRequest.getStatus(),
                adoptionRequest.getRequestDate(),
                adoptionRequest.getApprovedDate()
        );
    }

    @Override
    public List<AdoptionRequestDto> findAllAdoptionRequests() {
        return this.adoptionRequestRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AdoptionRequestDto> findAdoptionRequestsByUser(String username) {
        return this.adoptionRequestRepository.findAdoptionRequestsByUserUsername(username)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AdoptionRequestDto> createAdoptionRequest(String username, Long petId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));
        AdoptionRequest adoptionRequest = new AdoptionRequest(
                RequestStatus.PENDING,
                new Date(),
                null,
                pet,
                user
        );
        return Optional.of(this.adoptionRequestRepository.save(adoptionRequest))
                .map(this::toDto);
    }

    @Override
    public Optional<AdoptionRequestDto> updateAdoptionRequest(Long id, RequestStatus status, Date approvedDate) {
        AdoptionRequest adoptionRequest = this.adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
        adoptionRequest.setStatus(status);
        adoptionRequest.setApprovedDate(approvedDate);
        return Optional.of(this.adoptionRequestRepository.save(adoptionRequest))
                .map(this::toDto);
    }

    @Override
    public void deleteAdoptionRequest(Long id) {
        this.adoptionRequestRepository.deleteById(id);
    }
}
