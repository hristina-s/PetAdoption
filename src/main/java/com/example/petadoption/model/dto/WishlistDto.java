package com.example.petadoption.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class WishlistDto {
    private String username;
    private List<PetDto> pets;

    public WishlistDto() {}

    public WishlistDto(String username, List<PetDto> pets) {
        this.username = username;
        this.pets = pets;
    }
}
