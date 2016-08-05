package main.java.websocket.chatServer;


import main.java.service.session.SessionHandler;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.*;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Session;
/**
 * Created by enver on 8/1/16.
 */
@ApplicationScoped
public class ChatSessionHandler extends SessionHandler {
    private static final Set<Session> chatSessions = Collections.synchronizedSet(new HashSet<Session>());

    public void addSession(Session session) {
        chatSessions.add(session);
    }

    public void removeSession(Session session) {
        chatSessions.remove(session);
    }


    public Set<Session> getSessions(){
        return chatSessions;
    }


    private void sendToAllConnectedSessions(JsonObject message) throws IOException, EncodeException {
        for (Session s : chatSessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendObject(message);
            }
        }
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
