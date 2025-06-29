package com.example.petadoption.model.dto;

import com.example.petadoption.enumeration.RequestStatus;
import lombok.Data;

import java.util.Date;

@Data
public class AdoptionRequestDto {
    private RequestStatus status;
    private Date requestDate;
    private Date approvedDate;

    public AdoptionRequestDto() {}
    public AdoptionRequestDto(RequestStatus status, Date requestDate, Date approvedDate) {
        this.status = status;
        this.requestDate = requestDate;
        this.approvedDate = approvedDate;
    }
}
