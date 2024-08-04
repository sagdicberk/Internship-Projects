package com.eventTracking.Task5.controller;

import java.io.Console;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.concretes.JwtService;
import com.eventTracking.Task5.business.dto.UserDto;
import com.eventTracking.Task5.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuhtController {
	private final UserDetailsServiceImp service;
    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        service.CreateUser(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDto userDto) {
        String token = jwtService.authenticateAndGenerateToken(userDto);
        return ResponseEntity.ok(token);
    }
    

	
}
