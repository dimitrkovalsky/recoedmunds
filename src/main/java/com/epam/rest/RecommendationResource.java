package com.epam.rest;

import com.epam.beans.RecommendationBean;
import com.epam.errors.ApplicationException;
import com.epam.requests.LikeRequest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:34
 */
@Path("/reco")
@Stateless
public class RecommendationResource {
    @Inject
    private RecommendationBean recommendationBean;

    @POST
    @Path("/like")
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(String facebookId, LikeRequest request) throws ApplicationException {
        System.out.println("[RecommendationResource] POST like : " + request);
        recommendationBean.like(facebookId, request);
        return "Liked " + request.getUrl();
    }
}
