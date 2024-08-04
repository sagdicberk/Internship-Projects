package com.eventTracking.Task5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventTracking.Task5.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

	List<Notification> findByUsername(String username);

}
