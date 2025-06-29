package com.example.petadoption.model;

import com.example.petadoption.enumeration.RequestStatus;
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

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private Date requestDate;
    private Date approvedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public AdoptionRequest() {}

    public AdoptionRequest(RequestStatus status, Date requestDate, Date approvedDate, Pet pet, User user) {
        this.status = status;
        this.requestDate = requestDate;
        this.approvedDate = approvedDate;
        this.user = user;
        this.pet = pet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
