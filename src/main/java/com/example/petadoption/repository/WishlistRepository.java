package com.example.petadoption.repository;

import com.example.petadoption.model.User;
import com.example.petadoption.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findWishlistByUserUsername(String username);
    Optional<Wishlist> findWishlistByUser(User user);
}
