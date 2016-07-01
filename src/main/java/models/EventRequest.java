package main.java.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import main.java.CompositePKEY.EventRequest_PKEY;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="eventrequest")
@IdClass(EventRequest_PKEY.class)
public class EventRequest {
	
	@Id
	private String senderID;
	@Id
	private String eventID;
	
	private String message;
	
	
	public EventRequest(){}
	public EventRequest(String senderID, String eventID, String message){
		this.senderID=senderID;
		this.eventID=eventID;
		this.message=message;
	}
	
	public String getSender() {
		return senderID;
	}
	
	public void setSender(String sender) {
		this.senderID = sender;
	}
	
	public String getEvent() {
		return eventID;
	}
	
	public void setEvent(String event) {
		this.eventID = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

