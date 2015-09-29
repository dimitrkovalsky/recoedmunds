package com.epam.requests;

import com.epam.common.NotificationType;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:47
 */
public class NotificationRequest {
    private NotificationType notificationType;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}

