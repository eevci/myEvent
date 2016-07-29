package main.java.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Badge {
	
	private String name;
	private String category;
	private String pointToGet;
	private String description;
	//TO-DO set a picture for badge
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPointToGet() {
		return pointToGet;
	}
	public void setPointToGet(String pointToGet) {
		this.pointToGet = pointToGet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
