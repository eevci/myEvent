package main.java.CompositePKEY;

import java.io.Serializable;

public class Like_PKEY implements Serializable {
	protected String userID;
    protected String eventID;

    public Like_PKEY() {}

    public Like_PKEY(String userID, String eventID) {
        this.userID = userID;
        this.eventID = eventID;
    }
}
