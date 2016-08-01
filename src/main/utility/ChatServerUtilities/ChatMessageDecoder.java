package main.utility.ChatServerUtilities;

/**
 *  author enver
 */
import main.java.models.ChatMessage;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ChatMessage decode(final String textMessage) throws DecodeException {
        JsonObject obj = Json.createReader(new StringReader(textMessage))
                .readObject();
        return new ChatMessage(obj.getString("sender"),obj.getString("message"),new Date());
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}