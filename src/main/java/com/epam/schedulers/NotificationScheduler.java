package com.epam.schedulers;

import com.epam.errors.ApplicationException;
import com.epam.serices.Monitor;

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
    private Monitor monitor;

    @Schedule(second = "*/1", minute = "*", hour = "*")
    public void doWork() throws ApplicationException {
        monitor.process();
    }
}
