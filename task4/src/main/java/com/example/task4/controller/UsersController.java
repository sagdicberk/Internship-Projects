package com.example.task4.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task4.business.abstracts.UserDetailsServiceImp;
import com.example.task4.business.concretes.JwtService;
import com.example.task4.business.requests.UserDto;
import com.example.task4.business.responses.GetAllUserResponse;
import com.example.task4.business.responses.GetByIdUserResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UsersController {
	private final UserDetailsServiceImp service;
    private final JwtService jwtService;

    // Get all users (Admin only)
    @GetMapping("/admin/users")
    public ResponseEntity<List<GetAllUserResponse>> getAll() {
        List<GetAllUserResponse> users = service.getAll();
        return ResponseEntity.ok(users);
    }

    // Get user profile by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<GetByIdUserResponse> userProfile(@PathVariable long id) {
        GetByIdUserResponse user = service.getById(id);
        return ResponseEntity.ok(user);
    }

    // Get admin profile by ID (Admin only)
    @GetMapping("/admin/{id}")
    public ResponseEntity<GetByIdUserResponse> adminProfile(@PathVariable long id) {
        GetByIdUserResponse admin = service.getById(id);
        return ResponseEntity.ok(admin);
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto dto) {
        log.info("Registering user: " + dto);
        service.CreateUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Create a new user (Admin only)
    @PostMapping("/admin/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDto dto) {
        service.CreateUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    // Update user profile
    @PutMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody UserDto dto) {
        service.updateUser(id, dto);
        return ResponseEntity.noContent().build();
    }

    // Update admin profile (Admin only)
    @PutMapping("/admin/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable long id, @RequestBody UserDto dto) {
        service.updateUser(id, dto);
        return ResponseEntity.noContent().build();
    }

    // Delete user profile
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Delete admin profile (Admin only)
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

	// Generate JWT token for authentication
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) {
		String token = jwtService.authenticateAndGenerateToken(userDto);
		return ResponseEntity.ok(token);
	}

}
