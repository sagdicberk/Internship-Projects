package com.eventTracking.Task5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.eventTracking.Task5.business.abstracts.CategoryService;
import com.eventTracking.Task5.business.abstracts.EventService;
import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.concretes.NotificationService;
import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.model.Event;
import com.eventTracking.Task5.model.Notification;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/AllAuthenticateUsers")
public class AllAuthenticateUsersController {

    @Autowired
    private EventService eventService;

    @Autowired
    private NotificationService notificationService;
    
    @Autowired 
    public CategoryService categoryService;
    
    @Autowired
    public UserDetailsServiceImp serviceImp;

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

    // Notifications Management
    @GetMapping("/get/notifications")
    public ResponseEntity<List<Notification>> getNotifications(HttpServletRequest request) {
        String token = extractToken(request);
        List<Notification> notifications = notificationService.getNotifications(token);
        return ResponseEntity.ok(notifications);
    }

    // Event Participation Management
    @PostMapping("/{id}/join")
    public ResponseEntity<String> joinEvent(@PathVariable long id, HttpServletRequest request) {
        try {
            String token = extractToken(request);
            eventService.joinEvent(id, token);
            return ResponseEntity.ok("Joined event successfully");
        } catch (RuntimeException e) {
            return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/respond/{accept}/{notificationId}")
    public ResponseEntity<String> respondToInvitation(@PathVariable long id, @PathVariable boolean accept, @PathVariable long notificationId, HttpServletRequest request) {
        try {
            String token = extractToken(request);
            eventService.respondToInvitation(id, token, accept, notificationId);
            return ResponseEntity.ok(accept ? "Invitation accepted" : "Invitation declined");
        } catch (RuntimeException e) {
            return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
    


    // Event Retrieval Management
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> events = eventService.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/category/{id}/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEventsByCategoryId(@PathVariable Long id) {
        List<Event> events = eventService.getUpcomingEventsByCategoryId(id);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/next-week")
    public ResponseEntity<List<Event>> getEventsInNextWeek() {
        List<Event> events = eventService.getEventsInNextWeek();
        return ResponseEntity.ok(events);
    }
    


}
