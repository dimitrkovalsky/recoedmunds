package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.Notification;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 11:43
 */
public interface INotificationDao {
    void insert(Notification entity) throws DaoException;

    List<Notification> findAllByUser(String facebookId) throws DaoException;

    List<Notification> findAll() throws DaoException;

    Notification findById(ObjectId id) throws DaoException;

    void update(Notification entity) throws DaoException;
}
