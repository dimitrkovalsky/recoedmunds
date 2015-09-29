package com.epam.dao;

import com.epam.common.NotificationType;
import com.epam.errors.DaoException;
import com.epam.models.CarUpdate;
import com.epam.models.Notification;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 12:35
 */
public class CarUpdatesDao extends BasicDAO<CarUpdate, ObjectId> implements ICarUpdatesDao {
    public CarUpdatesDao(Datastore datastore) {
        super(datastore);
    }

    @Override
    public void insert(CarUpdate entity) throws DaoException {
        try {
            super.save(entity);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void insertAll(Collection<CarUpdate> entity) throws DaoException {
        try {
            for (CarUpdate update : entity) {
                insert(update);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarUpdate find(CarUpdate entity) throws DaoException {
        try {
            return super.findOne("_id", entity.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CarUpdate> findAllByFBId(String facebookId) throws DaoException {
        try {
            return getDatastore().find(CarUpdate.class).field("facebookId").equal(facebookId).asList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarUpdate findById(ObjectId id) throws DaoException {
        try {
            return super.findOne("_id", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CarUpdate entity) throws DaoException {
        try {
            super.save(entity);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Collection<CarUpdate> findUpdateByNotification(Notification notification) throws DaoException {

        try {
            Query query = getDatastore().find(CarUpdate.class);

            query.field("facebookId").equal(notification.getFacebookId());
            if (notification.getVin() != null) {
                query.field("vin").equal(notification.getVin());
            }
            if (notification.getMake() != null) {
                query.field("make").equal(notification.getMake());
            }
            if (notification.getModel() != null) {
                query.field("model").equal(notification.getModel());
            }
            if (notification.getYear() != null) {
                query.field("year").equal(notification.getYear());
            }
            if (notification.getStyleId() != null) {
                query.field("styleId").equal(notification.getStyleId());
            }
            if (notification.getPriceLowerThan() != null && NotificationType.PRICE_LOWER_THAN.equals(notification.getNotificationType())) {
                query.field("msrpPrice").lessThan(notification.getPriceLowerThan());//TODO check
            }
            Collection<CarUpdate> updates = query.asList();
            return updates;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
