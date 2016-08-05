package main.java.service.session;

import main.java.models.eventmodels.EventUser;
import main.java.models.securitymodels.Token;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.websocket.*;


public class SessionHandler {



    public void addSession(HttpSession session,EventUser user) {

        Token token=new Token(user,session.getId());
        Cookie cookie=new Cookie("TOKEN",token.getTokenValue());
        cookie.setMaxAge(24*60*60);

    }

    public void removeSession(HttpSession session) {

    }


    public boolean checkSession(HttpSession session){


        if(!session.getId().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }



}