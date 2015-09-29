package com.epam.beans;

import com.epam.common.DaoFactory;
import com.epam.errors.ApplicationException;
import com.epam.models.LikeModel;
import com.epam.requests.LikeRequest;
import org.bson.types.ObjectId;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:50
 */
public class RecommendationBean implements IRecommendationBean {

    public void like(String facebookId, LikeRequest request) throws ApplicationException {
        LikeModel model = toModel(request);
        model.setFacebookId(facebookId);
        DaoFactory.getLikesDao().insert(model);
    }

    private LikeModel toModel(LikeRequest request) {
        LikeModel model = new LikeModel();
        model.setId(new ObjectId());
        model.setLocation(request.getLocation());
        model.setPrice(request.getPrice());
        model.setStyle(request.getStyle());
        model.setTrim(request.getTrim());
        model.setVin(request.getVin());
        model.setUrl(request.getUrl());
        return model;
    }
}
