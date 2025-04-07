package com.example.petadoption.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String capacity;

    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets;

    @OneToMany(mappedBy = "shelter")
    private List<AnimalReport> animalReports;

    public Shelter() {}

    public Shelter(String name, String location, String capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }
}
