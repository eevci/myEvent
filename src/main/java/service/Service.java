package main.java.service;



import java.util.List;

import org.json.*;

import main.java.models.Event;
import main.java.models.EventRequest;
import main.java.models.EventUser;
import main.java.models.Like;

/**
*
* @author enver
*/

public interface Service {
	
	
	
	
	public void addPerson(EventUser person);
	
	public void addAdmin(String id);
	
	public void deletePerson(String id);
	
	public EventUser getPerson(String id);
	
	public List<EventUser> getAllPeople();
	
	public void updatePerson(EventUser person); 
	
	public boolean checkAdmin(String id);
	
	
	
	
	public void createEvent(Event event);
	
	public List<Event> getAllEvents();
	
	public void deleteEvent(String id);
	
	public void updateEvent(Event event);
	
	
	
	
	public void likeAnEvent(Like like);
	
	public List<Like> getLikersOfEvent(String eventID);

	public boolean joinRequestToAnEvent(String id, String eventId);
	
	public boolean answerARequest(String id,String eventId,boolean answer);
	
	public void cancelARequest(String id, String eventId);
	
	public boolean quitFromAnEvent(String id, String eventId);
	
	
	
	public List<EventRequest> getRequestsByUserId(String id);
	
	

	public List<Event> getEvents();
	
	public List<Event> getEventsByCategory(String category);

	public List<Event> getEventsByEventId(String id);
	
	public List<Event> getJoinedEventsByUserId(String id);
	
	public List<Event> getCreatedEventsByUserId(String id);

	public Event getEvent(String id);

	public List<EventRequest> getRequestByUserID(String id);

	public List<EventRequest> getRequestByEventID(String id);

	
	
	
	
	
	
	
	
	
}
