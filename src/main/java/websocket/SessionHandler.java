package main.java.websocket;

import main.java.models.ChatMessage;
import main.utility.ChatServerUtilities.ChatMessageDecoder;
import main.utility.ChatServerUtilities.ChatMessageEncoder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


public class SessionHandler {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());


    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }


    public Set<Session> getSessions(){
        return sessions;
    }



}