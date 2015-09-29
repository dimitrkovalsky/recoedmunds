package com.epam.beans;

import com.epam.errors.ApplicationException;
import com.epam.models.CarUpdate;
import com.epam.models.Notification;
import com.epam.requests.NotificationRequest;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:41
 */
public interface INotificationBean {
    void add(NotificationRequest request) throws ApplicationException;
    List<Notification> getAll() throws ApplicationException;

    List<CarUpdate> getUpdates(String facebookId) throws ApplicationException;

    void saveUpdate(CarUpdate carUpdate) throws ApplicationException;
}
