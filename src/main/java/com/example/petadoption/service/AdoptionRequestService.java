package com.example.petadoption.service;

import com.example.petadoption.enumeration.RequestStatus;
import com.example.petadoption.model.AdoptionRequest;
import com.example.petadoption.model.dto.AdoptionRequestDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdoptionRequestService {
    List<AdoptionRequestDto> findAllAdoptionRequests();
    List<AdoptionRequestDto> findAdoptionRequestsByUser(String username);
    Optional<AdoptionRequestDto> createAdoptionRequest(String username, Long petId);
    Optional<AdoptionRequestDto> updateAdoptionRequest(Long id, RequestStatus status, Date approvedDate);
    void deleteAdoptionRequest(Long id);
}
