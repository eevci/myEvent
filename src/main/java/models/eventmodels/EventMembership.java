package main.java.models.eventmodels;

import main.java.CompositePKEY.EventMembership_PKEY;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * author enver
 */
@XmlRootElement
@Entity
@Table(name="event_members")
@IdClass(EventMembership_PKEY.class)
public class EventMembership {

    @Id
    private String senderID;
    @Id
    private String eventID;

    private String membershipID;

    private String date;

    public EventMembership(){}
    public EventMembership(String senderID, String eventID, String membershipID, String date){
        this.senderID=senderID;
        this.eventID=eventID;
        this.membershipID=membershipID;
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

    public String getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(String membershipID) {
        this.membershipID = membershipID;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

}

