package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.Notification;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 11:42
 */
public class NotificationDao extends BasicDAO<Notification, ObjectId> implements INotificationDao {
    public NotificationDao(Datastore datastore) {
        super(datastore);
    }

    @Override
    public void insert(Notification entity) throws DaoException {
        try {
            super.save(entity);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Notification> findAllByUser(String facebookId) throws DaoException {
        try {
            return getDatastore().find(Notification.class).field("facebookId").equal(facebookId).asList();
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Notification> findAll() throws DaoException {
        try {
            return getDatastore().find(Notification.class).asList();
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Notification findById(ObjectId id) throws DaoException {
        try {
            return super.findOne("_id", id);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Notification entity) throws DaoException {
        try {
            super.save(entity);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

}