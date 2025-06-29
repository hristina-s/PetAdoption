package com.example.petadoption.service.impl;

import com.example.petadoption.enumeration.Role;
import com.example.petadoption.exceptions.UserNotFoundException;
import com.example.petadoption.model.User;
import com.example.petadoption.model.dto.UserDto;
import com.example.petadoption.repository.UserRepository;
import com.example.petadoption.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDto toDto(User user) {
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
    public List<UserDto> findAllUsers() {
        return this.userRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(this::toDto);
    }

    @Override
    public Optional<UserDto> create(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, Role role) {
        User user = new User(username, password, firstName, lastName, email, phoneNumber, address, role);
        return Optional.of(this.userRepository.save(user))
                .map(this::toDto);
    }

    @Override
    public Optional<UserDto> update(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, Role role) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setRole(role);
        return Optional.of(this.userRepository.save(user))
                .map(this::toDto);
    }

    @Override
    public void deleteUser(String username) {
        this.userRepository.deleteByUsername(username);
    }
}
