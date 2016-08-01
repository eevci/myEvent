package main.java.websocket.chatServer;


import main.java.websocket.SessionHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
/**
 * Created by enver on 8/1/16.
 */
@ApplicationScoped
public class ChatSessionHandler extends SessionHandler {
    private static final Set<Session> chatSessions = Collections.synchronizedSet(new HashSet<Session>());
    @Override
    public void addSession(Session session) {
        chatSessions.add(session);
    }
    @Override
    public void removeSession(Session session) {
        chatSessions.remove(session);
    }

    @Override
    public Set<Session> getSessions(){
        return chatSessions;
    }


    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
