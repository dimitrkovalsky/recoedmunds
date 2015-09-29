package com.epam.rest;

import com.epam.beans.INotificationBean;
import com.epam.errors.ApplicationException;
import com.epam.models.Inventory;
import com.epam.requests.NotificationRequest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
        notificationBean.subscribe(facebookId, request);
        return "Subscribed " + facebookId;
    }
}
