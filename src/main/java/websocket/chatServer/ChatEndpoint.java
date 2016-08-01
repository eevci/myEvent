package main.java.websocket.chatServer;

import main.java.models.ChatMessage;
import main.utility.ChatServerUtilities.ChatMessageDecoder;
import main.utility.ChatServerUtilities.ChatMessageEncoder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{room}/", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final ChatSessionHandler sessionHandler = new ChatSessionHandler();



    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        log.info("session openend and bound to room: " + room);
        session.getUserProperties().put("room", room);
        sessionHandler.addSession(session);
    }
    @OnClose
    public void close(final Session session) {
        log.info("session deleted");
        sessionHandler.removeSession(session);
    }

    @OnMessage
    public void onMessage(final Session session, final ChatMessage chatMessage) {
        String room = (String) session.getUserProperties().get("room");
        try {
            log.info("session "+chatMessage.getSender()+" send a message : " + chatMessage.getMessage());
            for (Session s : sessionHandler.getSessions()) {
                if (s.isOpen()
                        && room.equals(s.getUserProperties().get("room"))) {
                    s.getBasicRemote().sendObject(chatMessage);
                }
            }
        } catch (IOException | EncodeException e) {
            log.log(Level.WARNING, "onMessage failed", e);
        }
    }
}