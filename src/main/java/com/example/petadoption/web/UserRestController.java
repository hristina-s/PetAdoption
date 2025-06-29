package com.example.petadoption.web;

import com.example.petadoption.enumeration.Role;
import com.example.petadoption.model.dto.UserDto;
import com.example.petadoption.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> findUserByUsername(@PathVariable String username) {
        return this.userService.findUserByUsername(username)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> create(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String email,
                                          @RequestParam String phoneNumber,
                                          @RequestParam String address,
                                          @RequestParam Role role) {
        return this.userService.create(username, password, firstName, lastName, email, phoneNumber, address, role)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<UserDto> update(@PathVariable String username,
                                    @RequestParam String password,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String email,
                                    @RequestParam String phoneNumber,
                                    @RequestParam String address,
                                    @RequestParam Role role) {
        return this.userService.update(username, password, firstName, lastName, email, phoneNumber, address, role)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        this.userService.deleteUser(username);
    }
}
