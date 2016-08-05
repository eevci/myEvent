package main.java.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.models.eventmodels.EventMembership;
import main.java.service.session.AuthanticationHandler;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.eventmodels.Event;
import main.java.models.eventmodels.Like;
import main.java.service.Service;
import main.java.service.ServiceImpl;

/**
*
* @author enver
*/

@Path ("/event")
public class EventRestService {
	
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
	public Response getEvent(@PathParam("ID") String id,@Context HttpServletRequest request){
		try {
			if(authHandler.isAuthorized(request,"ALL")){
				JSONObject jo = new JSONObject();
				Event event = service.getEvent(id);
				if(!(event==null)){
					jo.accumulate("id", event.getId());
					jo.accumulate("name", event.getName());
					jo.accumulate("category", event.getCategory());
					jo.accumulate("address", event.getAddress());
					jo.accumulate("date", event.getDate());
					jo.accumulate("expiredate", event.getExpireDate());
					jo.accumulate("description", event.getDescription());
					jo.accumulate("place", event.getPlace());
					jo.accumulate("admin", event.getAdminID());
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
	public Response listEvent(@Context HttpServletRequest request){
		try {

			if(authHandler.isAuthorized(request,"ALL")) {


				JSONArray main = new JSONArray();
				List<Event> events = service.getAllEvents();
				for (Event event : events) {

					JSONObject jo = new JSONObject();
					jo.accumulate("id", event.getId());
					jo.accumulate("name", event.getName());
					jo.accumulate("category", event.getCategory());
					jo.accumulate("address", event.getAddress());
					jo.accumulate("date", event.getDate());
					jo.accumulate("expiredate", event.getExpireDate());
					jo.accumulate("description", event.getDescription());
					jo.accumulate("place", event.getPlace());
					jo.accumulate("admin", event.getAdminID());

					main.put(jo);
				}
				return Response.ok(main).header("Access-Control-Allow-Origin", "*")
						.build();
			}
			return Response.status(401).entity("No authorization").build();
		} catch (JSONException ex) {
			
		}
		return Response.serverError().build();
	}

	@GET
	@Path("/get/{eventID}/members")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEventMembers(@PathParam("eventID") String eventID,@Context HttpServletRequest request){
		try {

			if(authHandler.isAuthorized(request,"ALL")) {
				JSONArray main = new JSONArray();
				List<EventMembership> eventmemberships = service.getAllUsersOfEvent(eventID);
				for (EventMembership em : eventmemberships) {

					JSONObject jo = new JSONObject();
					jo.accumulate("membershipID", em.getMembershipID());
					jo.accumulate("userID", em.getSender());
					jo.accumulate("eventID", em.getEvent());
					jo.accumulate("date", em.getDate());
					main.put(jo);
				}
				return Response.ok(main).header("Access-Control-Allow-Origin", "*")
						.build();
			}
			return Response.status(401).entity("No authorization").build();
		} catch (JSONException ex) {

		}
		return Response.serverError().build();
	}

	@GET
	@Path("/get/{userID}/events-admin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEventsOfAUserAsAdmin(@PathParam("userID") String userID,@Context HttpServletRequest request){
		try {

			if(authHandler.isAuthorized(request,"ALL")) {

				JSONArray main = new JSONArray();
				List<Event> events = service.getCreatedEventsByUserId(userID);
				for (Event event : events) {

					JSONObject jo = new JSONObject();
					jo.accumulate("id", event.getId());
					jo.accumulate("name", event.getName());
					jo.accumulate("category", event.getCategory());
					jo.accumulate("address", event.getAddress());
					jo.accumulate("date", event.getDate());
					jo.accumulate("expiredate", event.getExpireDate());
					jo.accumulate("description", event.getDescription());
					jo.accumulate("place", event.getPlace());
					jo.accumulate("admin", event.getAdminID());

					main.put(jo);
				}
				return Response.ok(main).header("Access-Control-Allow-Origin", "*")
						.build();
			}
			return Response.status(401).entity("No authorization").build();
		} catch (JSONException ex) {

		}
		return Response.serverError().build();
	}

    @GET
    @Path("/get/{userID}/events")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEventsOfAUserAsUser(@PathParam("userID") String userID,@Context HttpServletRequest request){
        try {

			if(authHandler.isAuthorized(request,"ALL_EVENT")) {

				JSONArray main = new JSONArray();
				List<EventMembership> eventmemberships = service.getJoinedEventsByUserId(userID);
				for (EventMembership em : eventmemberships) {

					JSONObject jo = new JSONObject();
					jo.accumulate("membershipID", em.getMembershipID());
					jo.accumulate("userID", em.getSender());
					jo.accumulate("eventID", em.getEvent());
					jo.accumulate("date", em.getDate());
					main.put(jo);
				}
				return Response.ok(main).header("Access-Control-Allow-Origin", "*")
						.build();
			}
			return Response.status(401).entity("No authorization").build();
        } catch (JSONException ex) {

        }
        return Response.serverError().build();
    }
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public Response createEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID,@Context HttpServletRequest request ) {


		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
			service.createEvent(event);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();


	}
	@POST
	@Path("/add/member/{eventID}/{userID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUserToEvent(@PathParam("eventID") String eventID, @PathParam("userID") String userID ,@Context HttpServletRequest request) {
		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			service.addUserToEvent(userID,eventID, new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date()));
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();

	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteEvent(@PathParam("id") String id,@Context HttpServletRequest request) {
		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			service.deleteEvent(id);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();
	}

	@DELETE
	@Path("/delete/member/{membershipID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response quitFromAnEvent(@PathParam("membershipID") String membershipID ,@Context HttpServletRequest request) {
		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			service.quitFromAnEvent(membershipID);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();

	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID ,@Context HttpServletRequest request) {
		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
			service.updateEvent(event);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();

	}
	@GET
	@Path("/like/{eventID}")
	public Response getLikersOfAnEvent(@PathParam("eventID") String eventID,@Context HttpServletRequest request){

		try {
			if(authHandler.isAuthorized(request,"ALL")){
				JSONArray main = new JSONArray();
				List<Like> likeList=service.getLikersOfEvent(eventID);
				for(Like like : likeList){
					JSONObject jo = new JSONObject();
					jo.accumulate("id", like.getSenderID());
					main.put(jo);
				}
				return Response.ok(main).header("Access-Control-Allow-Origin", "*")
						.build();
			}
			return Response.status(401).entity("No authorization").build();
		} catch (JSONException ex) {

		}
		return Response.serverError().build();

	}
	@POST
	@Path("/like/{eventID}/{userID}")
	public Response likeEvent(@PathParam("eventID") String eventID,@PathParam("userID") String userID,@Context HttpServletRequest request){
		if(authHandler.isAuthorized(request,"LIMITED_EVENT")){
			Like like=new Like(eventID,userID);
			service.likeAnEvent(like);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.status(401).entity("No authorization").build();
	}
	
}
