package main.java.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.models.eventmodels.EventRequest;
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
	public Response getRequestByUserID(@PathParam("userID") String id,@Context HttpServletRequest request){
		try {
			List<EventRequest> requestlist = service.getRequestByUserID(id);
			JSONArray main = new JSONArray();
			for(EventRequest req: requestlist){
				
				JSONObject jo = new JSONObject();
			
				jo.accumulate("senderID", req.getSender());
				jo.accumulate("eventID", req.getEvent());
				jo.accumulate("message", req.getMessage());
				jo.accumulate("date", req.getDate());

				main.put(jo);
			}
			return Response.ok(main).header("Access-Control-Allow-Origin", "*")
				.build();
		} catch (JSONException ex) {
			return Response.serverError().build();
		}

	}
	
	@GET
	@Path("/get/{eventID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestByEventID(@PathParam("eventID") String id,@Context HttpServletRequest request){
		try {
			List<EventRequest> requestlist = service.getRequestByEventID(id);
			JSONArray main = new JSONArray();
			for(EventRequest req: requestlist){
				
				JSONObject jo = new JSONObject();
			
				jo.accumulate("senderID", req.getSender());
				jo.accumulate("eventID", req.getEvent());
				jo.accumulate("message", req.getMessage());
				jo.accumulate("date", req.getDate());
				
				main.put(jo);
			}
			return Response.ok(main).header("Access-Control-Allow-Origin", "*")
				.build();
		} catch (JSONException ex) {
			return Response.serverError().build();
		}

	}
	
	
	@POST
	@Path("/add/{senderID}/{eventID}/{date}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createRequest(@PathParam("senderID") String senderID,@PathParam("eventID") String eventID,@PathParam("date") String date, String message,@Context HttpServletRequest request) {

		try {
			service.joinRequestToAnEvent(new EventRequest(senderID,eventID,message,date));
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}

	}
	
	@DELETE
	@Path("/delete/{senderID}/{eventID}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteRequest(@PathParam("senderID") String senderID,@PathParam("eventID") String eventID,@Context HttpServletRequest request) {
		try {
			service.cancelARequest(senderID, eventID);
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/answer/{senderID}/{eventID}/{answer}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response answerRequest(@PathParam("senderID") String senderID,@PathParam("eventID") String eventID,@PathParam("answer") int answer,@Context HttpServletRequest request) {

		try {
			service.answerARequest(senderID,eventID,(answer==1));
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}

	}
	
	
}
