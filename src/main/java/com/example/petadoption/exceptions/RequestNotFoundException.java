package com.example.petadoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id) {
        super(String.format("Request with id %s not found", id));
    }
}
