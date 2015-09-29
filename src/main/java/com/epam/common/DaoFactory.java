package com.epam.common;

import com.epam.dao.*;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import java.lang.Integer;
import java.lang.String;
import java.net.UnknownHostException;

public class DaoFactory {
    private static String DATABASE_URL = "10.168.1.45";
    private static Integer DATABASE_PORT = 27017;
    private static String DATABASE_NAME = "liberty-database";
    private static Datastore datastore;

    static {
        try {
            Mongo mongo = new Mongo(DATABASE_URL, DATABASE_PORT);
            Morphia morphia = new Morphia();
            datastore = morphia.createDatastore(mongo, DATABASE_NAME);
        }
        catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }

    public static IInventoryDao getInventoryDao() {
        return new InventoryDao(datastore);
    }

    public static IUserDao getUserDao() {
        return new UserDao(datastore);
    }

    public static INotificationDao getNotificationDao() {
        return new NotificationDao(datastore);
    }

    public static ICarUpdatesDao getCarUpdatesDao() {
        return new CarUpdatesDao(datastore);
    }

    public static ILikesDao getLikesDao() {
        return new LikesDao(datastore);
    }
}