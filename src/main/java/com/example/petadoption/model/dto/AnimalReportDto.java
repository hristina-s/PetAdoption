package com.example.petadoption.model.dto;

import com.example.petadoption.enumeration.ReportType;
import lombok.Data;

import java.util.Date;

@Data
public class AnimalReportDto {
    private Date reportDate;
    private String location;
    private String description;
    private ReportType reportType;

    public AnimalReportDto() {}
    public AnimalReportDto(Date reportDate, String location, String description, ReportType reportType) {
        this.reportDate = reportDate;
        this.location = location;
        this.description = description;
        this.reportType = reportType;
    }
}
