package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.LikeModel;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:45
 */
public class LikesDao extends BasicDAO<LikeModel, ObjectId> implements ILikesDao {
    public LikesDao(Datastore datastore) {
        super(datastore);
    }

    @Override
    public void insert(LikeModel entity) throws DaoException {
        try {
            super.save(entity);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public LikeModel find(LikeModel entity) throws DaoException {
        try {
            return super.findOne("_id", entity.getId());
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<LikeModel> findAllByFbId(String facebookId) throws DaoException {
        try {
            return getDatastore().find(LikeModel.class).field("facebookId").equal(facebookId).asList();
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

}