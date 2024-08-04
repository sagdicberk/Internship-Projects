package com.eventTracking.Task5.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.abstracts.EventService;
import com.eventTracking.Task5.business.dto.EventDto;
import com.eventTracking.Task5.core.util.jwt.JwtUtil;
import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.model.Event;
import com.eventTracking.Task5.model.User;
import com.eventTracking.Task5.repository.CategoryRepository;
import com.eventTracking.Task5.repository.EventRepository;
import com.eventTracking.Task5.repository.UserRepository;

@Service
public class EventManager implements EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void createEvent(EventDto eventDto, String token) {
        validateToken(token);
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        
        Event event = convertDtoToEvent(eventDto);
        event.setAuthor(user.getUsername());
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(long id, EventDto eventDto) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existingEvent.setHeader(eventDto.getHeader());
        existingEvent.setDescription(eventDto.getDescription());
        existingEvent.setDate(eventDto.getDate());
        existingEvent.setCompletionDate(eventDto.getCompletionDate());
        Category category = categoryRepository.findById(eventDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingEvent.setCategory(category.getName());

        eventRepository.save(existingEvent);

        notifyParticipants(existingEvent);
        notificationService.notifyAdmins(existingEvent);
    }

    @Override
    public void deleteEvent(long id) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        notifyParticipants(existingEvent);
        notificationService.notifyAdmins(existingEvent);

        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public Event convertDtoToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setHeader(eventDto.getHeader());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        event.setCompletionDate(eventDto.getCompletionDate());
        
        Category category = categoryRepository.findById(eventDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
//        Category category = categoryRepository.findById(eventDto.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
        event.setCategory(category.getName());

        return event;
    }



    @Override
    public List<Event> getEventsByCategoryId(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return eventRepository.findByCategory(category);
    }

    @Override
    public void joinEvent(long eventId, String token) {
        validateToken(token);
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.getParticipants().contains(user.getUsername())) {
            event.getParticipants().add(user.getUsername());
            eventRepository.save(event);
            notificationService.sendNotification(
                user.getUsername(),
                "Başarılı bir şekilde aktiviteye katıldınız: " + event.getHeader()+ " Tarihi: " + event.getDate(),
                eventId,
                "info",
                true // Katılım yanıtı verilmiş olarak ayarla
            );
        } else {
            throw new RuntimeException("User has already joined the event");
        }
    }


    @Override
    public void sendJoinRequest(long eventId, String username, String adminToken) {
        validateAdminToken(adminToken);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        String message = "Bu aktiviteye dahil edildiniz: " + event.getHeader() + " Tarihi: " + event.getDate();
        notificationService.sendNotification(
            username,
            message,
            eventId,
            "invite", // Davet türü
            false // Yanıt verilmedi olarak ayarla
        );
    }


    @Override
    public void respondToInvitation(long eventId, String token, boolean accept, long notificationId) {
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email ile kullanıcı bulunamadı"));
        String username = user.getUsername();

        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

        // Bildirim gönderme işlemi
        if (accept) {
            if (!event.getParticipants().contains(username)) {
                event.getParticipants().add(username);
                eventRepository.save(event);
            }
            notificationService.sendNotification(username, "You have joined the event: " + event.getHeader(), eventId, "info", true); // Yanıt verildi olarak ayarla
        } else {
            notificationService.sendNotification(username, "You have declined the invitation to the event: " + event.getHeader(), eventId, "info", true); // Yanıt verildi olarak ayarla
        }

        // Bildirimi yanıtla
        notificationService.updateNotificationRespondedStatus(notificationId);
    }

    @Override
    public void notifyParticipants(Event existingEvent) {
        Event event = eventRepository.findById(existingEvent.getId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        for (String participant : event.getParticipants()) {
            notificationService.sendNotification(
                participant,
                "Update on event: " + event.getHeader(),
                existingEvent.getId(),
                "info", // Bilgilendirme türü
                false // Yanıt verilmedi olarak ayarla
            );
        }
    }


    @Override
    public List<Event> getUpcomingEvents() {
        LocalDate today = LocalDate.now();
        return eventRepository.findByDateAfter(today);
    }
    


    @Override
    public List<Event> getUpcomingEventsByCategoryId(long id) {
        LocalDate today = LocalDate.now();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return eventRepository.findByDateAfterAndCategory(today, category.getName());
    }

    @Override
    public List<Event> getEventsInNextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);
        return eventRepository.findByDateBetween(today, nextWeek);
    }

    private void validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("JWT Token is missing or empty");
        }
        if (jwtUtil.extractEmail(token) == null) {
            throw new RuntimeException("email could not be extracted from JWT Token");
        }
    }

    private void validateAdminToken(String token) {
        validateToken(token);
        if (!jwtUtil.isAdmin(token)) {
            throw new RuntimeException("Admin privileges required");
        }
    }


}
