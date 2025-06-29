package com.example.petadoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShelterNotFoundException extends RuntimeException {
    public ShelterNotFoundException(Long id) {
        super(String.format("Shelter with id %s not found", id));
    }
}
