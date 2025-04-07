package com.example.petadoption.model;

import com.example.petadoption.enumeration.ReportType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class AnimalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date reportDate;
    private String location;
    private String description;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public AnimalReport() {}
    public AnimalReport(Date reportDate, String location, String description, ReportType reportType, User user, Shelter shelter) {
        this.reportDate = reportDate;
        this.location = location;
        this.description = description;
        this.reportType = reportType;
        this.user = user;
        this.shelter = shelter;
    }
}
