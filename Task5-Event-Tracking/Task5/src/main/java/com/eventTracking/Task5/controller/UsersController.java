package com.eventTracking.Task5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.concretes.JwtService;
import com.eventTracking.Task5.business.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UsersController {
	private final UserDetailsServiceImp service;
    private final JwtService jwtService;


    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto dto) {
        log.info("Registering user: " + dto);
        service.CreateUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

	// Generate JWT token for authentication
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) {
		String token = jwtService.authenticateAndGenerateToken(userDto);
		return ResponseEntity.ok(token);
	}

}
