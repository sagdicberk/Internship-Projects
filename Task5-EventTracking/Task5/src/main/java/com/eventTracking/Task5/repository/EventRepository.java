package com.eventTracking.Task5.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByCategory(Category category);

	List<Event> findByDateAfter(LocalDate currentDate);

	List<Event> findByDateAfterAndCategory(LocalDate currentDate, String categoryName);

	List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
