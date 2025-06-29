package com.example.petadoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException(String username) {
        super(String.format("Wishlist for user %s not found", username));
    }
}
