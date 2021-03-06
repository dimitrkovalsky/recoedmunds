package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.CarUpdate;
import com.epam.models.Notification;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:15
 */
public interface ICarUpdatesDao {
    void insert(CarUpdate entity) throws DaoException;

    void insertAll(Collection<CarUpdate> entity) throws DaoException;

    CarUpdate find(CarUpdate entity) throws DaoException;

    List<CarUpdate> findAllByFBId(String facebookId) throws DaoException;

    CarUpdate findById(ObjectId id) throws DaoException;

    void update(CarUpdate entity) throws DaoException;

    Collection<CarUpdate> findUpdateByNotification(Notification notification) throws DaoException;
}
