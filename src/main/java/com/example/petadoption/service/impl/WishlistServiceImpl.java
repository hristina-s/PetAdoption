package com.example.petadoption.service.impl;

import com.example.petadoption.exceptions.PetNotFoundException;
import com.example.petadoption.exceptions.UserNotFoundException;
import com.example.petadoption.exceptions.WishlistNotFoundException;
import com.example.petadoption.model.Pet;
import com.example.petadoption.model.User;
import com.example.petadoption.model.Wishlist;
import com.example.petadoption.model.dto.PetDto;
import com.example.petadoption.model.dto.WishlistDto;
import com.example.petadoption.repository.PetRepository;
import com.example.petadoption.repository.UserRepository;
import com.example.petadoption.repository.WishlistRepository;
import com.example.petadoption.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    private WishlistDto toDto(Wishlist wishlist) {
        List<PetDto> petDtos = wishlist.getPets()
                .stream()
                .map(this::convertToPetDto).toList();

        return new WishlistDto(
                wishlist.getUser().getUsername(),
                petDtos
        );

    }
    private PetDto convertToPetDto(Pet pet) {
        return new PetDto(
                pet.getName(),
                pet.getAge(),
                pet.getGender(),
                pet.getType(),
                pet.getImage()
        );
    }
    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, PetRepository petRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Optional<WishlistDto> findWishlistByUser(String username) {
        return wishlistRepository.findWishlistByUserUsername(username)
                .map(this::toDto);
    }

    @Override
    public Optional<WishlistDto> addToWishlist(String username, Long petId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));
        Wishlist wishlist = this.wishlistRepository.findWishlistByUser(user)
                .orElseGet(() -> {
                            Wishlist newWishlist = new Wishlist();
                            newWishlist.setUser(user);
                            newWishlist.setPets(new ArrayList<>());
                            return newWishlist;
                        }
                );
        if (!wishlist.getPets().contains(pet)) {
            wishlist.getPets().add(pet);
            return Optional.of(this.wishlistRepository.save(wishlist))
                    .map(this::toDto);
        }
        return Optional.of(wishlist)
                .map(this::toDto);
    }

    @Override
    public Optional<WishlistDto> removeFromWishlist(String username, Long petId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));
        Wishlist wishlist = this.wishlistRepository.findWishlistByUser(user)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (wishlist.getPets().contains(pet)) {
            wishlist.getPets().remove(pet);
            return Optional.of(this.wishlistRepository.save(wishlist))
                    .map(this::toDto);
        }
        return Optional.of(wishlist)
                .map(this::toDto);
    }

    @Override
    public Optional<WishlistDto> clearWishlist(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Wishlist wishlist = this.wishlistRepository.findWishlistByUser(user)
                .orElseThrow(() -> new WishlistNotFoundException(username));
        wishlist.getPets().clear();
        return Optional.of(this.wishlistRepository.save(wishlist))
                .map(this::toDto);
    }
}
