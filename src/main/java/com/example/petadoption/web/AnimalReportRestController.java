package com.example.petadoption.web;

import com.example.petadoption.enumeration.ReportType;
import com.example.petadoption.model.dto.AnimalReportDto;
import com.example.petadoption.service.AnimalReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class AnimalReportRestController {

    private final AnimalReportService animalReportService;

    public AnimalReportRestController(AnimalReportService animalReportService) {
        this.animalReportService = animalReportService;
    }

    @GetMapping
    public List<AnimalReportDto> findAllAnimalReports() {
        return this.animalReportService.findAllAnimalReports();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AnimalReportDto> findReportById(@PathVariable Long id) {
        return this.animalReportService.findReportById(id)
                .map(report -> ResponseEntity.ok().body(report))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{username}")
    public List<AnimalReportDto> findAnimalReportsByUser(@PathVariable String username) {
        return this.animalReportService.findAnimalReportsByUser(username);
    }

    @PostMapping("/add")
    public ResponseEntity<AnimalReportDto> createAnimalReport(@RequestParam String username,
                                                        @RequestParam String location,
                                                        @RequestParam String description,
                                                        @RequestParam ReportType reportType,
                                                        @RequestParam Long shelterId) {
        return this.animalReportService.createAnimalReport(username, location, description, reportType, shelterId)
                .map(report -> ResponseEntity.ok().body(report))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AnimalReportDto> updateAnimalReport(@PathVariable Long id,
                                                        @RequestParam String location,
                                                        @RequestParam String description,
                                                        @RequestParam ReportType reportType) {
        return this.animalReportService.updateAnimalReport(id, location, description, reportType)
                .map(report -> ResponseEntity.ok().body(report))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimalReport(@PathVariable Long id) {
        this.animalReportService.deleteAnimalReport(id);
    }
}
