package com.epam.rest;

import com.epam.beans.INotificationBean;
import com.epam.errors.ApplicationException;
import com.epam.models.CarUpdate;
import com.epam.requests.NotificationRequest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(String facebookId, NotificationRequest request) throws ApplicationException {
        System.out.println("[NotificationResource] POST add : " + request);
        notificationBean.monitor(facebookId, request);
        return "Subscribed " + facebookId;
    }

    @GET
    @Path("/updates")
    @Produces(MediaType.APPLICATION_JSON)
    private List<CarUpdate> getUpdates(String facebookId) throws ApplicationException {
        return notificationBean.getUpdates(facebookId);
    }
}
