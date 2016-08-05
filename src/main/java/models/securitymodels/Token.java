package main.java.models.securitymodels;

import main.java.models.eventmodels.EventUser;

import javax.websocket.Session;
import java.util.Date;

/**
 * Created by enver on 8/3/16.
 */
public class Token {


    private String tokenValue;

    public Token(EventUser user, String sessionID){

        tokenValue = createToken(user, sessionID);

    }

    public String createToken(EventUser user, String sessionID){

        StringBuilder tokenBuilder=new StringBuilder();
        tokenBuilder.append("$9msk!");
        tokenBuilder.append("$"+user.getUsername()+"½"+user.getRole().toString()+"¾"+sessionID);

        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBuilder.toString());

    }
    public String getTokenValue(){
        return this.tokenValue;
    }


}
