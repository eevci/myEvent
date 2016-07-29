package main.java.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
public class Event {

	@Id
	private String id;
	private String name;
	private Date date;
	private Date expireDate;
	//private List<EventUser> eventUsers;
	private String place;
	private String category;
	private String description;
	private String address;
	private boolean online;
	private String adminID;
	
	public Event(String id, String name, java.sql.Date date, java.sql.Date expireDate, String place,
			String category, String description, String address, boolean online, String adminID) {
		this.id=id;
		this.name=name;
		this.date=date;
		this.expireDate=expireDate;
		this.place=place;
		this.category=category;
		this.description=description;
		this.address=address;
		this.online=online;
		this.setAdminID(adminID);
	}
	public Event(){}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
}
