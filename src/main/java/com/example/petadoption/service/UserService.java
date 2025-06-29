package com.example.petadoption.service;

import com.example.petadoption.enumeration.Role;
import com.example.petadoption.model.User;
import com.example.petadoption.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserByUsername(String username);
    Optional<UserDto> create(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, Role role);
    Optional<UserDto> update(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, Role role);
    void deleteUser(String username);
}
