package com.example.petadoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(Long id) {
        super(String.format("Report with id %s not found", id));
    }
}
