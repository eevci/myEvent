package main.java.service.session;

import com.google.gson.Gson;
import main.java.models.eventmodels.EventUser;
import main.java.models.securitymodels.Token;
import main.java.service.Service;
import main.java.service.ServiceImpl;
import main.utility.myEventConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by enver on 8/3/16.
 */
public class AuthanticationHandler {

    private Map<myEventConstants.UserRole,List<String>> roles ;

    private static AuthanticationHandler authanticationHandler = new AuthanticationHandler( );

    private AuthanticationHandler(){
        roles=new HashMap<>();

        roles.put(myEventConstants.UserRole.SUPERADMIN,new ArrayList<String>() {{
            add("ALL_USER");
            add("ALL_ADMIN");
            add("CRUD_ADMIN");
            add("ALL_EVENT");
            add("ALL");

        }});

        roles.put(myEventConstants.UserRole.ADMIN,new ArrayList<String>() {{
            add("ALL_USER");
            add("ALL_ADMIN");
            add("ALL_EVENT");
            add("ALL");
        }});

        roles.put(myEventConstants.UserRole.MEMBER,new ArrayList<String>() {{
            add("LIMITED_USER");
            add("LIMITED_EVENT");
            add("ALL");
        }});

        roles.put(myEventConstants.UserRole.ANONYMOUS,new ArrayList<String>() {{
            add("ALL");
        }});
    }
    public static synchronized AuthanticationHandler getInstance(){
        return authanticationHandler;
    }


    public boolean isAuthorized(HttpServletRequest request, String action){

        SessionHandler sessionHandler=new SessionHandler();
        sessionHandler.checkSession(request.getSession());

        Service service=new ServiceImpl();
        String userID="~";
        String cookieTOKEN="~";
        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals("username")){
                userID=cookie.getValue();
            }
            else if(cookie.getName().equals("TOKEN")){
                cookieTOKEN=cookie.getValue();
            }
        }

        if(userID.equals("~")) return false;

        EventUser user=service.getPerson(userID);


        Token token=new Token(user, request.getSession().getId());
        String tokenValue=token.getTokenValue();

        if(!tokenValue.equals(cookieTOKEN)) return false;



        myEventConstants.UserRole role=user.getRole();

        System.out.println(roles.size());
        List<String> listOfActions=roles.get(role);

        if (!listOfActions.isEmpty())
            return listOfActions.contains(action);
        else return false;
    }


}
