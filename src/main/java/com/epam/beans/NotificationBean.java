package com.epam.beans;

import com.epam.common.DaoFactory;
import com.epam.errors.ApplicationException;
import com.epam.errors.DaoException;
import com.epam.models.CarUpdate;
import com.epam.models.Notification;
import com.epam.requests.NotificationRequest;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:41
 */
public class NotificationBean implements INotificationBean {


//    @Override
//    public void monitor(String facebookId, NotificationRequest request) throws ApplicationException {
//        INotificationDao dao = DaoFactory.getNotificationDao();
//
//        switch (request.getNotificationType()) {
//            case PRICE_LOWER_THAN:
//                savePriceMonitoringNotification(request);
//                break;
//            case CAR_AVAILABLE:
//                Notification notification = createBaseNotification(request);
//                notification.setNotificationType(NotificationType.CAR_AVAILABLE);
//                saveNotification(notification);
//                break;
//            case MONITOR_ALL:
//                Notification baseNotification = createBaseNotification(request);
//                baseNotification.setNotificationType(NotificationType.MONITOR_ALL);
//                saveNotification(baseNotification);
//                break;
//        }
//    }

    @Override
    public void add(NotificationRequest request) throws ApplicationException {
        savePriceMonitoringNotification(request);
    }

    @Override
    public List<Notification> getAll() throws ApplicationException {
        return  DaoFactory.getNotificationDao().findAll();
    }

    @Override
    public List<CarUpdate> getUpdates(String facebookId) throws ApplicationException {
        return DaoFactory.getCarUpdatesDao().findAllByFBId(facebookId);
    }

    @Override
    public void saveUpdate(CarUpdate carUpdate) throws ApplicationException {
        DaoFactory.getCarUpdatesDao().insert(carUpdate);
    }

    private void savePriceMonitoringNotification(NotificationRequest request) throws DaoException {
        Notification notification = createBaseNotification(request);
        saveNotification(notification);
    }

    private void saveNotification(Notification notification) throws DaoException {
        DaoFactory.getNotificationDao().insert(notification);
    }

    private Notification createBaseNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setId(new ObjectId());
        notification.setStyleId(request.getStyleId());
        notification.setFacebookId(request.getFacebookId());
        notification.setPriceLowerThan(request.getPriceLowerThan());
        notification.setVin(request.getVin());
        notification.setNotificationType(request.getNotificationType());
        return notification;
    }
}
