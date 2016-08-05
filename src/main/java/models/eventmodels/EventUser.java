package main.java.models.eventmodels;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import main.utility.myEventConstants;
import org.json.JSONObject;

/**
*
* @author enver
*/


@XmlRootElement
@Entity
public class EventUser {
	@Id
	private String ID;
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private int age;
	private String university;

	//TO-DO Profile Picture must be added
	
	private myEventConstants.UserRole role;
	private int scope;
	//private List <Badge> badges;
	
	public EventUser(){}
	public EventUser(String id,String name, String surname, String email,String password,int age, String university,int scope,myEventConstants.UserRole role){
		this.ID=id;
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.password=password;
		this.age=age;
		this.university=university;
		this.scope=scope;
		this.role=role;
	}
	
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getUsername(){
		return this.ID;
	}
	public void setUsername(String ID){
		this.ID=ID;
	}
	
	public String getSurname(){
		return this.surname;
	}
	public void setSurname(String surname){
		this.surname=surname;
	}
	
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	
	public myEventConstants.UserRole getRole(){
		return this.role;
	}
	
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	
	
	
	
	
}
