package main.java.models;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

import main.java.CompositePKEY.EventRequest_PKEY;

/**
*
* @author enver
*/

@XmlRootElement
@Entity
@Table(name="event_like")
@IdClass(EventRequest_PKEY.class)
public class Like {
	@Id
	private String eventID;
	@Id
	private String senderID;
	
	
	public Like(){}
	public Like(String eventID, String senderID){
		this.senderID=senderID;
		this.eventID=eventID;
		
	}
	public String getSenderID(){
		return this.senderID;
	}
	public String getEventID(){
		return this.eventID;
	}
	public void setSenderID(String senderID){
		this.senderID=senderID;
	}
	public void setEventID(String eventID){
		this.eventID=eventID;
	}

}
