package com.eventTracking.Task5.business.abstracts;

import java.util.List;

import com.eventTracking.Task5.business.dto.EventDto;
import com.eventTracking.Task5.model.Event;

public interface EventService {
	void createEvent(EventDto eventDTO, String token);
    void updateEvent(long id, EventDto eventDTO);
    void deleteEvent(long id);
    List<Event> getAllEvents();
    Event getEventById(long id);
    void joinEvent(long eventId, String username);
	Event convertDtoToEvent(EventDto eventDto);
	
}
