package com.eventTracking.Task5.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventTracking.Task5.model.Category;
import com.eventTracking.Task5.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByCategory(Category category);

	// Tarihi geçmemiş (aktif) etkinlikleri listele
	List<Event> findByDateAfter(LocalDate currentDate);

	// Tarihi geçmemiş ve belirli bir kategoriye ait etkinlikleri listele
	List<Event> findByDateAfterAndCategory(LocalDate currentDate, String categoryName);

	// 1 hafta içerisinde gerçekleşecek etkinlikleri listele
	List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
