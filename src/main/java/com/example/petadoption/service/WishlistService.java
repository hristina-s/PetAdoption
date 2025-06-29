package com.example.petadoption.service;

import com.example.petadoption.model.Wishlist;
import com.example.petadoption.model.dto.WishlistDto;

import java.util.Optional;

public interface WishlistService {
    Optional<WishlistDto> findWishlistByUser(String username);
    Optional<WishlistDto> addToWishlist(String username, Long PetId);
    Optional<WishlistDto> removeFromWishlist(String username, Long PetId);
    Optional<WishlistDto> clearWishlist(String username);
}
