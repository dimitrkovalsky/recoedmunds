package com.epam.schedulers;

import com.epam.beans.INotificationBean;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 14:42
 */
@Singleton
public class NotificationScheduler {
    @Inject
    private INotificationBean notificationBean;

    @Schedule(second = "*/1", minute = "*", hour = "*")
    public void doWork() {
        System.out.println("[NotificationScheduler] updates");
    }
}
