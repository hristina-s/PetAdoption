package com.example.petadoption.model;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionRequest> adoptionRequests;

    @ManyToMany(mappedBy = "pets")
    private List<Wishlist> wishlists;

    public Pet() {}

    public Pet(String name, String age, Gender gender, PetType type, byte[] image, Shelter shelter, AdoptionRequest adoptionRequests) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.type = type;
        this.image = image;
        this.shelter = shelter;
    }
}
