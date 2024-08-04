package com.eventTracking.Task5.business.abstracts;

import java.util.List;

import com.eventTracking.Task5.business.dto.EventDto;
import com.eventTracking.Task5.model.Event;

public interface EventService {
	
	// for CRUD event
	void createEvent(EventDto eventDTO, String token);
    void updateEvent(long id, EventDto eventDTO);
    void deleteEvent(long id);
    
    
    // listeleme 
    List<Event> getAllEvents();
    Event getEventById(long id);
    List<Event> getEventsByCategoryId(long categoryId); 
    
    

   	
    // mapper converter
    Event convertDtoToEvent(EventDto eventDto);
	
	
    void joinEvent(long id, String token); 
    
    // admin send request to user
    void sendJoinRequest(long eventId, String username, String adminToken);
    // user reply
    void respondToInvitation(long eventId, String username, boolean accept,long notificationId);
         
    
    void notifyParticipants(Event event);                   
    
    
    // Yaklaşan tüm etkinlikleri listele
    List<Event> getUpcomingEvents();
    
    // Yaklaşan etkinlikleri belirli bir kategoriye göre listele
    List<Event> getUpcomingEventsByCategoryId(long id);
    
 // 1 hafta içinde gerçekleşecek etkinlikleri listele
    List<Event> getEventsInNextWeek();

}
