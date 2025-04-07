package com.example.petadoption.repository;

import com.example.petadoption.model.AnimalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalReportRepository extends JpaRepository<AnimalReport, Long> {
}
