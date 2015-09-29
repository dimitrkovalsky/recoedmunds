package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.CarUpdate;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 12:35
 */
public class CarUpdatesDao extends BasicDAO<CarUpdate, ObjectId> implements ICarUpdatesDao {
    public CarUpdatesDao(Datastore datastore) {
        super(datastore);
    }

    @Override
    public void insert(CarUpdate entity) throws DaoException {
        try {
            super.save(entity);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarUpdate find(CarUpdate entity) throws DaoException {
        try {
            return super.findOne("_id", entity.getId());
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CarUpdate> findAll(String facebookId) throws DaoException {
        try {
            return getDatastore().find(CarUpdate.class).field("facebookId").equal(facebookId).asList();
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarUpdate findById(ObjectId id) throws DaoException {
        try {
            return super.findOne("_id", id);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CarUpdate entity) throws DaoException {
        try {
            super.save(entity);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
