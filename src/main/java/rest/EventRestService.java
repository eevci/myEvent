package main.java.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.Event;
import main.java.models.EventUser;
import main.java.models.Like;
import main.java.service.Service;
import main.java.service.ServiceImpl;

/**
*
* @author enver
*/

@Path ("/event")
public class EventRestService {
	
	Service service = new ServiceImpl().getInstance();

	@GET
	@Path("/test")
	public String test(){
		return "test...";			
	}
	
	@GET
	@Path("/get/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvent(@PathParam("ID") String id){
		try {
			
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
	public Response listEvent(){
		try {
			JSONArray main = new JSONArray();
			List <Event> events = service.getAllEvents();
			for(Event event : events){

				JSONObject jo = new JSONObject();
				jo.accumulate("id", event.getId());
				jo.accumulate("name", event.getName());
				jo.accumulate("category", event.getCategory());
				jo.accumulate("address", event.getAddress());
				jo.accumulate("date", event.getDate());
				jo.accumulate("expiredate", event.getExpireDate());
				jo.accumulate("description", event.getDescription());
				jo.accumulate("place", event.getPlace());
				
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
	public Response createEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID ) {
		Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
		service.createEvent(event);
		return Response.ok().header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteEvent(@PathParam("id") String id) {
		service.deleteEvent(id);
		return Response.ok().header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID) {
		Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
		service.updateEvent(event);
		return Response.ok().header("Access-Control-Allow-Origin", "*")
				.build();
	}
	@GET
	@Path("/like/{eventID}")
	public Response getLikersOfAnEvent(@PathParam("eventID") String eventID){

		try {
			JSONArray main = new JSONArray();
			List<Like> likeList=service.getLikersOfEvent(eventID);
			for(Like like : likeList){
				JSONObject jo = new JSONObject();
				jo.accumulate("id", like.getSenderID());
				main.put(jo);
			}
			return Response.ok(main).header("Access-Control-Allow-Origin", "*")
					.build();
		} catch (JSONException ex) {

		}
		return Response.serverError().build();

	}
	@POST
	@Path("/like/{eventID}/{userID}")
	public Response likeEvent(@PathParam("eventID") String eventID,@PathParam("userID") String userID){
		Like like=new Like(eventID,userID);
		service.likeAnEvent(like);
		return Response.ok().header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
}
