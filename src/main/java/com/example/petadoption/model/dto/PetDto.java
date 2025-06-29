package com.example.petadoption.model.dto;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import lombok.Data;

@Data
public class PetDto {
    private String name;
    private String age;
    private Gender gender;
    private PetType type;
    private byte[] image;

    public PetDto() {}
    public PetDto(String name, String age, Gender gender, PetType type, byte[] image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.type = type;
        this.image = image;
    }

}
