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

    /**
     * Extracts JWT token from request header.
     * 
     * @param request HttpServletRequest
     * @return Extracted JWT token as a String
     */
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("JWT Token is missing or invalid");
    }

    /**
     * Handles exceptions in a centralized way.
     * 
     * @param e Exception
     * @param status HttpStatus
     * @return ResponseEntity containing the error message
     */
    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        return ResponseEntity.status(status).body(e.getMessage());
    }

    // Notifications Management

    /**
     * Get notifications for the authenticated user.
     * 
     * @param request HttpServletRequest
     * @return ResponseEntity containing the list of notifications
     */
    @GetMapping("/get/notifications")
    public ResponseEntity<List<Notification>> getNotifications(HttpServletRequest request) {
        String token = extractToken(request);
        List<Notification> notifications = notificationService.getNotifications(token);
        return ResponseEntity.ok(notifications);
    }

    // Event Participation Management

    /**
     * Join an event.
     * 
     * @param id Event ID
     * @param request HttpServletRequest
     * @return ResponseEntity with success or error message
     */
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

    /**
     * Respond to an event invitation.
     * 
     * @param id Event ID
     * @param accept True if accepting the invitation, false otherwise
     * @param request HttpServletRequest
     * @return ResponseEntity with success or error message
     */
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
    
    /**
     * Get all categories.
     * 
     * @return ResponseEntity containing the list of categories
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
    


    // Event Retrieval Management

    /**
     * Get a list of upcoming events.
     * 
     * @return ResponseEntity containing the list of upcoming events
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> events = eventService.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    /**
     * Get a list of upcoming events filtered by category.
     * 
     * @param categoryId Category ID
     * @return ResponseEntity containing the list of upcoming events in the specified category
     */
    @GetMapping("/category/{id}/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEventsByCategoryId(@PathVariable Long id) {
        List<Event> events = eventService.getUpcomingEventsByCategoryId(id);
        return ResponseEntity.ok(events);
    }

    /**
     * Get a list of events happening in the next week.
     * 
     * @return ResponseEntity containing the list of events in the next week
     */
    @GetMapping("/next-week")
    public ResponseEntity<List<Event>> getEventsInNextWeek() {
        List<Event> events = eventService.getEventsInNextWeek();
        return ResponseEntity.ok(events);
    }
    


}
