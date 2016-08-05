package main.java.rest;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.core.*;

import main.java.models.securitymodels.Token;
import main.java.service.session.AuthanticationHandler;
import main.utility.myEventConstants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.eventmodels.EventUser;
import main.java.service.Service;
import main.java.service.ServiceImpl;

/**
*
* @author enver
*/

@Path ("/user")
public class UserRestService {
	
	Service service = new ServiceImpl().getInstance();
	AuthanticationHandler authHandler= AuthanticationHandler.getInstance();

	@GET
	@Path("/test")
	public String test(){
		return "test...";			
	}
	@GET
	@Path("/get/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("ID") String id,@Context HttpServletRequest request){
		try {

			if(authHandler.isAuthorized(request,"ALL")) {
				JSONObject jo = new JSONObject();
				EventUser user = service.getPerson(id);
				if (!(user == null)) {
					jo.accumulate("id", user.getUsername());
					jo.accumulate("name", user.getName());
					jo.accumulate("surname", user.getSurname());
					jo.accumulate("age", user.getAge());
					jo.accumulate("email", user.getEmail());
					jo.accumulate("university", user.getUniversity());
					jo.accumulate("scope", user.getScope());
					jo.accumulate("role", user.getRole());
				}


				return Response.ok(jo).header("Access-Control-Allow-Origin", "*")
						.build();
			}

			return Response.status(401).entity("No authorization").build();

		} catch (JSONException ex) {
			
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPerson(@Context HttpServletRequest request){
		try {
			if(authHandler.isAuthorized(request,"ALL_USER")) {
				JSONArray main = new JSONArray();
				List<EventUser> users = service.getAllPeople();
				for (EventUser user : users) {

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
			}
			return Response.status(401).entity("No authorization").build();
		} catch (JSONException ex) {
			return Response.serverError().build();
		}

	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addPerson(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("surname") String surname,@QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("age") int age,@QueryParam("university") String university,@QueryParam("scope") int scope,@QueryParam("type") myEventConstants.UserRole role,@Context HttpServletRequest request) {
		
		try{
			if(authHandler.isAuthorized(request,"ALL")) {
				String digestedPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
				EventUser person = new EventUser(id, name, surname, email, digestedPass, age, university, scope,role );
				service.addPerson(person);
				if (role == myEventConstants.UserRole.ADMIN || role==myEventConstants.UserRole.SUPERADMIN) service.addAdmin(id);

				return Response.status(200).entity("success").build();
			}
			return Response.status(401).entity("No authorization").build();
		}
		catch(Exception ex){
			return Response.serverError().build();
		}
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePerson(@PathParam("id") String id,@Context HttpServletRequest request) {
		try{
			if(service.checkAdmin(id)){
				if(authHandler.isAuthorized(request,"CRUD_ADMIN")){
					service.deletePerson(id);
					return Response.status(200).entity("success").build();
				}
			}
			else{
				if(authHandler.isAuthorized(request,"LIMITED_USER")){
					service.deletePerson(id);
					return Response.status(200).entity("success").build();
				}

			}
			return Response.status(401).entity("No authorization").build();


		}
		catch(Exception ex){
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updatePerson(@QueryParam("ID") String id, @QueryParam("name")String name, @QueryParam("surname") String surname, @QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("age") int age, @QueryParam("university") String university, @QueryParam("scope") int scope, @QueryParam("type") myEventConstants.UserRole role, @Context HttpServletRequest request) {



		EventUser person=new EventUser(id, name, surname, email, password, age, university, scope,role);

		try{
			if(person.getRole()== myEventConstants.UserRole.ADMIN || person.getRole()== myEventConstants.UserRole.SUPERADMIN) {
				if (!authHandler.isAuthorized(request, "ALL_USER")) {
					service.updatePerson(person);
					return Response.status(200).entity("success").build();
				}
			}
			else{
				if (!authHandler.isAuthorized(request, "LIMITED_USER")) {
					service.updatePerson(person);
					return Response.status(200).entity("success").build();
				}
			}
			return Response.status(401).entity("No authorization").build();
		}
		catch(Exception ex){
			return Response.serverError().build();
		}

	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@QueryParam("ID") String id,@QueryParam("password") String pass,@Context HttpServletRequest request){
		try{

			myEventConstants consts=new myEventConstants();
			EventUser person=service.getPerson(id);

			NewCookie tcookie=new NewCookie("TOKEN",(new Token(person,request.getSession().getId())).getTokenValue(),"/myEvent/","localhost",1,"",12*60*60,false);

			NewCookie idcookie=new NewCookie("username",id,"/myEvent/","localhost",1,"",12*60*60,false);
			String digestedPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);

			if(digestedPass.equals(person.getPassword())){
				
				if(person.getRole()== myEventConstants.UserRole.ADMIN || person.getRole()== myEventConstants.UserRole.SUPERADMIN){ // if user is admin
					return Response.status(200).cookie(tcookie,idcookie).entity("1").build();
				}
				else{
					return Response.status(200).cookie(tcookie,idcookie).entity("0").build();
				}
			}
			else{ // if password is wrong
				return Response.status(200).entity("e1").build();
			}

		}
		catch(Exception ex){
			ex.printStackTrace();
			return Response.status(200).entity(ex).build();
		}
	}

	
	
}
