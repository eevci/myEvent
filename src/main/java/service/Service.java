package main.java.service;



import java.util.List;

import main.java.models.*;
import org.json.*;

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

	public String authenticateUser(String id, String password);
	
	
	public void createEvent(Event event);
	
	public List<Event> getAllEvents();
	
	public void deleteEvent(String id);
	
	public void updateEvent(Event event);
	
	public void addUserToEvent(String userID, String eventID, String date);

	public List<EventMembership> getAllUsersOfEvent(String eventID);
	
	public void likeAnEvent(Like like);
	
	public List<Like> getLikersOfEvent(String eventID);

	public void joinRequestToAnEvent(EventRequest request);
	
	public void answerARequest(String id,String eventId,boolean answer);
	
	public void cancelARequest(String id, String eventId);
	
	public void quitFromAnEvent(String id);
	

	
	public List<Event> getEventsByCategory(String category);
	
	public List<EventMembership> getJoinedEventsByUserId(String id);
	
	public List<Event> getCreatedEventsByUserId(String id);

	public Event getEvent(String id);

	public List<EventRequest> getRequestByUserID(String id);

	public List<EventRequest> getRequestByEventID(String id);

	
	
	
	
	
	
	
	
	
}
