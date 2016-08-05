package main.java.models.eventmodels;

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

	private String date;
	
	public EventRequest(){}
	public EventRequest(String senderID, String eventID, String message, String date){
		this.senderID=senderID;
		this.eventID=eventID;
		this.message=message;
		this.date=date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}

