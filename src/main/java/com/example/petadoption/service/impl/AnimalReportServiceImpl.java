package com.example.petadoption.service.impl;

import com.example.petadoption.enumeration.ReportType;
import com.example.petadoption.exceptions.ReportNotFoundException;
import com.example.petadoption.exceptions.ShelterNotFoundException;
import com.example.petadoption.exceptions.UserNotFoundException;
import com.example.petadoption.model.AnimalReport;
import com.example.petadoption.model.Shelter;
import com.example.petadoption.model.User;
import com.example.petadoption.model.dto.AnimalReportDto;
import com.example.petadoption.model.dto.ShelterDto;
import com.example.petadoption.model.dto.UserDto;
import com.example.petadoption.repository.AnimalReportRepository;
import com.example.petadoption.repository.ShelterRepository;
import com.example.petadoption.repository.UserRepository;
import com.example.petadoption.service.AnimalReportService;
import com.example.petadoption.service.ShelterService;
import com.example.petadoption.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalReportServiceImpl implements AnimalReportService {

    private final AnimalReportRepository animalReportRepository;
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;

    public AnimalReportServiceImpl(AnimalReportRepository animalReportRepository, UserRepository userRepository, ShelterRepository shelterRepository) {
        this.animalReportRepository = animalReportRepository;
        this.userRepository = userRepository;
        this.shelterRepository = shelterRepository;
    }

    private AnimalReportDto toDto(AnimalReport animalReport) {
        return new AnimalReportDto(
                animalReport.getReportDate(),
                animalReport.getLocation(),
                animalReport.getDescription(),
                animalReport.getReportType()
        );
    }

    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress()
        );
    }

    @Override
    public List<AnimalReportDto> findAllAnimalReports() {
        return this.animalReportRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalReportDto> findReportById(Long id) {
        return this.animalReportRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public List<AnimalReportDto> findAnimalReportsByUser(String username) {
        return this.animalReportRepository.findAnimalReportsByUserUsername(username)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalReportDto> createAnimalReport(String username, String location, String description, ReportType reportType, Long shelterId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Shelter shelter = this.shelterRepository.findById(shelterId)
                .orElseThrow(() -> new ShelterNotFoundException(shelterId));
        AnimalReport animalReport = new AnimalReport(
                new Date(),
                location,
                description,
                reportType,
                user,
                shelter
        );
        return Optional.of(this.animalReportRepository.save(animalReport))
                .map(this::toDto);
    }

    @Override
    public Optional<AnimalReportDto> updateAnimalReport(Long id, String location, String description, ReportType reportType) {
        AnimalReport animalReport = this.animalReportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
        animalReport.setLocation(location);
        animalReport.setDescription(description);
        animalReport.setReportType(reportType);
        return Optional.of(this.animalReportRepository.save(animalReport))
                .map(this::toDto);
    }

    @Override
    public void deleteAnimalReport(Long id) {
        this.animalReportRepository.deleteById(id);
    }
}
