package com.epam.beans;

import com.epam.requests.NotificationRequest;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:41
 */
public interface INotificationBean {
    void subscribe(String facebookId, NotificationRequest request);
}
