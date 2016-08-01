package main.java.rest;

import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.security.MessageDigest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.EventUser;
import main.java.service.Service;
import main.java.service.ServiceImpl;

/**
*
* @author enver
*/

@Path ("/user")
public class UserRestService {
	
	Service service = new ServiceImpl().getInstance();

	@GET
	@Path("/test")
	public String test(){
		return "test...";			
	}
	@GET
	@Path("/get/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("ID") String id){
		try {
			
			
			JSONObject jo = new JSONObject();
			EventUser user = service.getPerson(id);
			if(!(user==null)){
				jo.accumulate("id", user.getUsername());
				jo.accumulate("name", user.getName());
				jo.accumulate("surname", user.getSurname());
				jo.accumulate("age", user.getAge());
				jo.accumulate("email", user.getEmail());
				jo.accumulate("university", user.getUniversity());
				jo.accumulate("scope", user.getScope());
			}
			
			
			return Response.ok(jo).header("Access-Control-Allow-Origin", "*")
				.build();
		} catch (JSONException ex) {
			
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPerson(){
		try {
			JSONArray main = new JSONArray();
			List <EventUser> users = service.getAllPeople();
			for(EventUser user : users){
				
				JSONObject jo = new JSONObject();
				jo.accumulate("id", user.getUsername());
				jo.accumulate("name", user.getName());
				jo.accumulate("surname", user.getSurname());
				jo.accumulate("age", user.getAge());
				jo.accumulate("email", user.getEmail());
				jo.accumulate("university", user.getUniversity());
				jo.accumulate("scope", user.getScope());
				
				main.put(jo);
			}
			return Response.ok(main).header("Access-Control-Allow-Origin", "*")
				.build();
		} catch (JSONException ex) {
			
		}
		return Response.serverError().build();
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addPerson(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("surname") String surname,@QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("age") int age,@QueryParam("university") String university,@QueryParam("scope") int scope,@QueryParam("type") int type) {
		
		try{
			String digestedPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
			EventUser person=new EventUser(id, name, surname, email, digestedPass, age, university, scope);
			service.addPerson(person);
			if(type==1) service.addAdmin(id);

			return Response.status(200).entity("success").build();
		}
		catch(Exception ex){
			return Response.serverError().build();
		}
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePerson(@PathParam("id") String id) {
		try{
			service.deletePerson(id);
			return Response.status(200).entity("success").build();
		}
		catch(Exception ex){
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updatePerson(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("surname") String surname,@QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("age") int age,@QueryParam("university") String university,@QueryParam("scope") int scope) {
		EventUser person=new EventUser(id, name, surname, email, password, age, university, scope);
		
		try{
			service.updatePerson(person);
			return Response.status(200).entity("success").build();
		}
		catch(Exception ex){
			return Response.serverError().build();
		}
		
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@QueryParam("ID") String id,@QueryParam("password") String pass){
		try{
			
			EventUser person=service.getPerson(id);


			String digestedPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);
			System.out.println(person.getPassword());
			System.out.println(digestedPass);
			if(digestedPass.equals(person.getPassword())){
				
				if(service.checkAdmin(id)){ // if user is admin
					return Response.status(200).entity("1").build();
				}
				else{
					return Response.status(200).entity("0").build();
				}
			}
			else{ // if password is wrong
				return Response.status(200).entity("e1").build();
			}
			
		}
		catch(Exception ex){
			return Response.status(200).entity("e2").build();
		}
	}
	
	
}
