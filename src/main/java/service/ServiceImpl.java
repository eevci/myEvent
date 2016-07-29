package main.java.service;

import java.util.List;

import org.json.JSONObject;

import main.java.models.Event;
import main.java.models.EventRequest;
import main.java.models.EventUser;
import main.java.models.Like;
import main.utility.HibernateUtility;

/**
*
* @author enver
*/

public class ServiceImpl implements Service{
	
	HibernateUtility hibernateUtility= new HibernateUtility();
	private static Service instance = null;
	
	
	public static Service getInstance() {
		if (instance == null) {
			return new ServiceImpl();
		} else {
			return instance;
		}
	}
	
	
	
	public void addPerson(EventUser person){
		hibernateUtility.save(person);
	}
	public void addAdmin(String id){
		hibernateUtility.addAdmin(id);
	}
	public EventUser getPerson(String id){
		return (EventUser) hibernateUtility.get(EventUser.class, id,"id").get(0);
	}
	public boolean checkAdmin(String id){
		return hibernateUtility.checkAdmin(id);	
	}
	public List<EventUser> getAllPeople(){
		return hibernateUtility.get(EventUser.class);
	}
	public void deletePerson(String id){
		hibernateUtility.delete(EventUser.class, id);
	}
	
	public void updatePerson(EventUser person){
		hibernateUtility.update(person);
	}
	
	
	
	
	
	public void createEvent(Event event){
		hibernateUtility.save(event);
	}
	public Event getEvent(String id){
		return (Event) hibernateUtility.get(Event.class, id,"id").get(0);
	}
	public List<Event> getAllEvents(){
		return hibernateUtility.get(Event.class);
	}
	public void deleteEvent(String id){
		hibernateUtility.delete(Event.class, id);
	}
	public void updateEvent(Event event){
		hibernateUtility.update(event);
	}
	
	
	
	
	public void likeAnEvent(Like like){
		hibernateUtility.save(like);
	}
	public boolean joinRequestToAnEvent(String id){
		return false;
	}
	
	@Override
	public boolean joinRequestToAnEvent(String id, String eventId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean answerARequest(String id, String eventId, boolean answer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void cancelARequest(String id, String eventId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean quitFromAnEvent(String id, String eventId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	@Override
	public List<EventRequest> getRequestsByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getEventsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getEventsByEventId(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getJoinedEventsByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Event> getCreatedEventsByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<EventRequest> getRequestByUserID(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<EventRequest> getRequestByEventID(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Like> getLikersOfEvent(String eventID) {
		return hibernateUtility.get(Like.class,eventID,"eventID");
	}
	
}
