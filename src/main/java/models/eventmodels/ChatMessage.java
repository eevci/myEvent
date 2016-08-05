package main.java.models.eventmodels;

/**
 *  author enver
 */

import java.util.Date;

public class ChatMessage {
    private String message;
    private String sender;
    private Date received;

    public ChatMessage(String sender,String message,Date received){
        this.message=message;
        this.sender=sender;
        this.received=received;
    }
    public String getSender(){
        return this.sender;
    }
    public String getMessage(){
        return this.message;
    }
    public Date getReceived(){
        return this.received;
    }
}