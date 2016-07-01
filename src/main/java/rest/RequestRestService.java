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
import main.java.models.EventRequest;
import main.java.models.EventUser;
import main.java.service.Service;
import main.java.service.ServiceImpl;

/**
*
* @author enver
*/

@Path ("/request")
public class RequestRestService {
	
	Service service = new ServiceImpl().getInstance();

	@GET
	@Path("/test")
	public String test(){
		return "test...";			
	}
	
	@GET
	@Path("/getreq/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestByUserID(@PathParam("userID") String id){
		try {
			List<EventRequest> requestlist = service.getRequestByUserID(id);
			JSONArray main = new JSONArray();
			for(EventRequest req: requestlist){
				
				JSONObject jo = new JSONObject();
			
				jo.accumulate("senderID", req.getSender());
				jo.accumulate("eventID", req.getEvent());
				jo.accumulate("message", req.getMessage());
				
				main.put(jo);
			}
			return Response.ok(main).header("Access-Control-Allow-Origin", "*")
				.build();
		} catch (JSONException ex) {
			
		}
		return Response.serverError().build();
	}
	
	@GET
	@Path("/get/{eventID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestByEventID(@PathParam("eventID") String id){
		try {
			List<EventRequest> requestlist = service.getRequestByEventID(id);
			JSONArray main = new JSONArray();
			for(EventRequest req: requestlist){
				
				JSONObject jo = new JSONObject();
			
				jo.accumulate("senderID", req.getSender());
				jo.accumulate("eventID", req.getEvent());
				jo.accumulate("message", req.getMessage());
				
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
	public String createEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID) {
		Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
		service.createEvent(event);
		return "success...";
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEvent(@PathParam("id") String id) {
		service.deleteEvent(id);
		return "success...";
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEvent(@QueryParam("ID") String id,@QueryParam("name")String name, @QueryParam("date") Date date,@QueryParam("expireDate") Date expireDate,@QueryParam("place") String place,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("address") String address, @QueryParam("online") boolean online,@QueryParam("admin") String adminID) {
		Event event=new Event(id, name, date, expireDate, place, category, description, address,online,adminID);
		service.updateEvent(event);
		return "success...";
	}
	
	
}
