package com.example.petadoption.model.dto;

import lombok.Data;

@Data
public class ShelterDto {
    private String name;
    private String location;
    private String capacity;

    public ShelterDto() {}
    public ShelterDto(String name, String location, String capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }
}
