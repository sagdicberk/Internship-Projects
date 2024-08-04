package com.eventTracking.Task5.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.core.util.jwt.JwtUtil;
import com.eventTracking.Task5.model.Event;
import com.eventTracking.Task5.model.Notification;
import com.eventTracking.Task5.model.User;
import com.eventTracking.Task5.repository.NotificationRepository;
import com.eventTracking.Task5.repository.UserRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository; // Assuming you have a repository for notifications
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public void sendNotification(String username, String message, long eventId, String type, boolean responded) {
        Notification notification = new Notification();
        notification.setUsername(username);
        notification.setMessage(message);
        notification.setEventId(eventId);
        notification.setTimestamp(new Date());
        notification.setType(type);
        notification.setResponded(responded); // Yanıt durumu

        notificationRepository.save(notification);
    }
    
    public void updateNotificationRespondedStatus(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setResponded(true);
        notificationRepository.save(notification);
    }
    
    public void notifyAdmins(Event event) {
        List<String> adminUsernames = getAdminUsernames(); // Admin kullanıcı adlarını al
        for (String admin : adminUsernames) {
            sendNotification(
                admin,
                "A event '" + event.getHeader() + "' has been changed or created by " + event.getAuthor(),
                event.getId(),
                "info", // Bildirim türü (info)
                false // Yanıt verilmedi (false)
            );
        }
    }

    // Gerçek admin kullanıcı adlarını veritabanından alır
    private List<String> getAdminUsernames() {
        List<User> admins = userRepository.findByRolesContaining("ADMIN");
        return admins.stream()
                     .map(User::getUsername)
                     .collect(Collectors.toList());
    }
    
    public List<Notification> getNotifications(String token) {
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        return notificationRepository.findByUsername(user.getUsername());
    }
}
