package main.java.CompositePKEY;

import java.io.Serializable;

public class EventRequest_PKEY implements Serializable {
	protected String eventID;
    protected String senderID;

    public EventRequest_PKEY() {}

    public EventRequest_PKEY(String eventID, String senderID) {
        this.senderID = senderID;
        this.eventID = eventID;
    }
}
