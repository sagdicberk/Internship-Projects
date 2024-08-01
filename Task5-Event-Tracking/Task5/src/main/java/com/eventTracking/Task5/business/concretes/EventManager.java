package com.eventTracking.Task5.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.abstracts.EventService;
import com.eventTracking.Task5.business.dto.EventDto;
import com.eventTracking.Task5.core.util.jwt.JwtUtil;
import com.eventTracking.Task5.model.Event;
import com.eventTracking.Task5.repository.EventRepository;

@Service
public class EventManager implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void createEvent(EventDto eventDto, String token) {
		// Validate and extract username from token
		if (token == null || token.isEmpty()) {
			throw new RuntimeException("JWT Token is missing or empty");
		}

		String username = jwtUtil.extractUsername(token);
		if (username == null) {
			throw new RuntimeException("Username could not be extracted from token");
		}

		// Convert DTO to entity and set the author
		Event event = convertDtoToEvent(eventDto);
		event.setAuthor(username);

		// Save the event to the repository
		eventRepository.save(event);
		;

	}

	@Override
	public void updateEvent(long id, EventDto eventDto) {
		Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));

		existingEvent.setName(eventDto.getName());
		existingEvent.setLocation(eventDto.getLocation());
		existingEvent.setDate(eventDto.getDate());

		eventRepository.save(existingEvent);
	}

	@Override
	public void deleteEvent(long id) {
		eventRepository.deleteById(id);

	}

	@Override
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public Event getEventById(long id) {
		Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
		return event;
	}

	@Override
	public Event convertDtoToEvent(EventDto eventDto) {
		Event event = new Event();
		event.setName(eventDto.getName());
		event.setLocation(eventDto.getLocation());
		event.setDate(eventDto.getDate());

		return event;
	}

	@Override
	public void joinEvent(long eventId, String token) {
		 // Validate and extract username from token
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("JWT Token is missing or empty");
        }

        String username = jwtUtil.extractUsername(token);
        if (username == null) {
            throw new RuntimeException("Username could not be extracted from token");
        }

        // Find the event by ID
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Add the username to the list of participants if not already present
        if (!event.getParticipants().contains(username)) {
            event.getParticipants().add(username);
            eventRepository.save(event);
        } else {
            throw new RuntimeException("User already joined the event");
        }
	}

}
