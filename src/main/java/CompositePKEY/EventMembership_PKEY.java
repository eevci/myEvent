package main.java.CompositePKEY;

/**
 * author enver
 */
import java.io.Serializable;

public class EventMembership_PKEY implements Serializable {
    protected String senderID;
    protected String eventID;

    public EventMembership_PKEY() {}

    public EventMembership_PKEY(String senderID, String eventID) {
        this.senderID = senderID;
        this.eventID = eventID;
    }
}
