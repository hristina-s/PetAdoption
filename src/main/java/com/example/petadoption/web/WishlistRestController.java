package com.example.petadoption.web;

import com.example.petadoption.model.dto.WishlistDto;
import com.example.petadoption.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistRestController {

    private final WishlistService wishlistService;

    public WishlistRestController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<WishlistDto> findWishlistByUser(@PathVariable String username) {
        return this.wishlistService.findWishlistByUser(username)
                .map(wishlist -> ResponseEntity.ok().body(wishlist))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<WishlistDto> addToWishlist(@RequestParam String username,
                                                     @RequestParam Long petId) {
        return  this.wishlistService.addToWishlist(username, petId)
                .map(wishlist -> ResponseEntity.ok().body(wishlist))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<WishlistDto> removeFromWishlist(@RequestParam String username,
                                                          @RequestParam Long petId) {
        return this.wishlistService.removeFromWishlist(username, petId)
                .map(wishlist -> ResponseEntity.ok().body(wishlist))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/clear")
    public ResponseEntity<WishlistDto> clearWishlist(@RequestParam String username) {
        return this.wishlistService.clearWishlist(username)
                .map(wishlist -> ResponseEntity.ok().body(wishlist))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
