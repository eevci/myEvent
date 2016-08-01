package main.java.websocket;


import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
/**
 * Created by enver on 8/1/16.
 */
@ApplicationScoped
public class ChatSessionHandler {
    private int deviceId = 0;
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private final Set<String> devices = new HashSet<>();
    public void addSession(Session session) {
        sessions.add(session);
        for (String device : devices) {
            JsonObject addMessage = createAddMessage(device);
            //sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public List<String> getDevices() {
        return new ArrayList<>(devices);
    }

    public void addDevice(String device) {
    }

    public void removeDevice(int id) {
    }

    public void toggleDevice(int id) {
    }

    private String getDeviceById(int id) {
        return null;
    }
    public Set<Session> getSessions(){
        return sessions;
    }
    private JsonObject createAddMessage(String device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device)
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
