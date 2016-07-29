package main.java.service;

import java.util.ArrayList;
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
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
		return (EventUser) hibernateUtility.get(EventUser.class, valueList,columnNameList).get(0);
	}
	public boolean checkAdmin(String id){
		return hibernateUtility.checkAdmin(id);	
	}
	public List<EventUser> getAllPeople(){
		return hibernateUtility.get(EventUser.class);
	}
	public void deletePerson(String id){
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
		hibernateUtility.delete(EventUser.class, valueList,columnNameList);
	}
	
	public void updatePerson(EventUser person){
		hibernateUtility.update(person);
	}
	
	
	
	
	
	public void createEvent(Event event){
		hibernateUtility.save(event);
	}
	public Event getEvent(String id){
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
	    return (Event) hibernateUtility.get(Event.class, valueList,columnNameList).get(0);
	}
	public List<Event> getAllEvents(){
		return hibernateUtility.get(Event.class);
	}
	public void deleteEvent(String id){
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");

        valueList.add(id);

	    hibernateUtility.delete(Event.class, valueList,columnNameList);
	}
	public void updateEvent(Event event){
		hibernateUtility.update(event);
	}
	
	
	
	
	public void likeAnEvent(Like like){
		hibernateUtility.save(like);
	}

	public List<Like> getLikersOfEvent(String eventID) {
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("eventID");

        valueList.add(eventID);

		return hibernateUtility.get(Like.class,valueList,columnNameList);
	}

	public void joinRequestToAnEvent(EventRequest request) { hibernateUtility.save(request); }
	public void answerARequest(String id, String eventId, boolean answer) {
		if(answer==true){
			System.out.println("Answer is true");
		}
		else{
			System.out.println("Answer is false");
			cancelARequest(id,eventId);
		}

	}

	public void cancelARequest(String id, String eventId) {
	    List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("senderID");
        columnNameList.add("eventID");

        valueList.add(id);
        valueList.add(eventId);
	    hibernateUtility.delete(EventRequest.class, valueList,columnNameList);
	}
	@Override
	public void quitFromAnEvent(String id, String eventId) {
		// TODO Auto-generated method stub

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


	public List<EventRequest> getRequestByUserID(String id) {
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("senderID");

        valueList.add(id);
		return hibernateUtility.get(EventRequest.class, valueList,columnNameList);
	}

	public List<EventRequest> getRequestByEventID(String id) {
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("eventID");

        valueList.add(id);
		return hibernateUtility.get(EventRequest.class, valueList,columnNameList);
	}




	
}
