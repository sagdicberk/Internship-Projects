package com.eventTracking.Task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventTracking.Task5.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
}
