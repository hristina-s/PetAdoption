package com.example.petadoption.service;

import com.example.petadoption.enumeration.ReportType;
import com.example.petadoption.model.AnimalReport;
import com.example.petadoption.model.dto.AnimalReportDto;

import java.util.List;
import java.util.Optional;

public interface AnimalReportService {
    List<AnimalReportDto> findAllAnimalReports();
    Optional<AnimalReportDto> findReportById(Long id);
    List<AnimalReportDto> findAnimalReportsByUser(String username);
    Optional<AnimalReportDto> createAnimalReport(String username, String location, String description, ReportType reportType, Long shelterId);
    Optional<AnimalReportDto> updateAnimalReport(Long id, String location, String description, ReportType reportType);
    void deleteAnimalReport(Long id);
}
