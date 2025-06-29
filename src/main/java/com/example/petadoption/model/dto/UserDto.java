package com.example.petadoption.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public UserDto() {}
    public UserDto(String username, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
