package com.epam.common;

import com.epam.dao.IInventoryDao;
import com.epam.dao.IUserDao;
import com.epam.dao.InventoryDao;
import com.epam.dao.UserDao;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import java.lang.Integer;
import java.lang.String;
import java.net.UnknownHostException;

public class DaoFactory {
    private static String DATABASE_URL = "localhost";
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
}