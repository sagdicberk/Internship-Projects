package com.eventTracking.Task5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventTracking.Task5.business.abstracts.CategoryService;
import com.eventTracking.Task5.business.abstracts.EventService;
import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.dto.EventDto;
import com.eventTracking.Task5.business.dto.categoryDto;
import com.eventTracking.Task5.model.Event;
import com.eventTracking.Task5.model.User;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserDetailsServiceImp userService;
    
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("JWT Token is missing or invalid");
    }


    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        return ResponseEntity.status(status).body(e.getMessage());
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    // Event Management
    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody EventDto eventDto, HttpServletRequest request) {
        try {
            String token = extractToken(request);
            eventService.createEvent(eventDto, token);
            return ResponseEntity.ok("Event created successfully");
        } catch (Exception e) {
            return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable long id ){
    	Event event = eventService.getEventById(id);
    	return ResponseEntity.ok(event);
    }
    
    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable long id, @RequestBody EventDto eventDto) {
        try {
            eventService.updateEvent(id, eventDto);
            return ResponseEntity.ok("Event updated successfully");
        } catch (RuntimeException e) {
            return handleException(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Event deleted successfully");
        } catch (RuntimeException e) {
            return handleException(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/events/{id}/request/{username}")
    public ResponseEntity<String> sendJoinRequest(@PathVariable long id, @PathVariable String username, HttpServletRequest request) {
        try {
            String token = extractToken(request);
            eventService.sendJoinRequest(id, username, token);
            return ResponseEntity.ok("Join request sent successfully");
        } catch (RuntimeException e) {
            return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Category Management
    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@RequestBody categoryDto categoryDto) {
        try {
            categoryService.createCategory(categoryDto);
            return ResponseEntity.ok("Category created successfully");
        } catch (Exception e) {
            return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
