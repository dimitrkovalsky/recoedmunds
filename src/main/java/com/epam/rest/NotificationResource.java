package com.epam.rest;

import com.epam.beans.INotificationBean;
import com.epam.beans.IUserBean;
import com.epam.common.NotificationType;
import com.epam.errors.ApplicationException;
import com.epam.models.CarUpdate;
import com.epam.models.User;
import com.epam.requests.NotificationRequest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:40
 */
@Path("/notifications")
@Stateless
public class NotificationResource {
    @Inject
    private INotificationBean notificationBean;

    @Inject
    private IUserBean userBean;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(NotificationRequest request) throws ApplicationException {
        System.out.println("[NotificationResource] POST add : " + request);
        request.setNotificationType(NotificationType.PRICE_LOWER_THAN);
        User user = new User();
        user.setFacebookId(request.getFacebookId());
        user.setAccessToken("CAACEdEose0cBAGxNRzBCdbmlaAWE3JWMYJxrEM4TcRPvner5ntzC7oPqeUiBK6Mmeabn40GFa2ozAUQF5ZAAP0H7jK0fkIdHZBZA6v8qtTEsInDLgAETZAFvAY8ZAMSQ9QVLevFvnpnIZAhHVCMQJTDQt0Ta3FY7ZB6nzbbFztLCkQbLqL7Ib5Od7RHV7jA2OHDRLLDeZCZAdnwZDZD");
        userBean.addUser(user);
        notificationBean.add(request);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity("")
                .build();
    }

    @POST
    @Path("/updates")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    private List<CarUpdate> getUpdates(String facebookId) throws ApplicationException {
        return notificationBean.getUpdates(facebookId);
    }
}
