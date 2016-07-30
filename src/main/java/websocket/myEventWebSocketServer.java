package main.java.websocket;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
/**
 * Created by Enver on 30/7/2016.
 */
@ServerEndpoint("/actions")
public class myEventWebSocketServer {

    @Inject
    private myEventSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
    }
}
