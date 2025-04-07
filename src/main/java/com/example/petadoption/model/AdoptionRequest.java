package com.example.petadoption.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Date requestDate;
    private Date approvedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public AdoptionRequest() {}

    public AdoptionRequest(String status, Date requestDate, Date approvedDate, Pet pet, User user) {
        this.status = status;
        this.requestDate = requestDate;
        this.approvedDate = approvedDate;
        this.user = user;
        this.pet = pet;
    }
}
