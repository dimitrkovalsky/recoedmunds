package com.epam.serices;

import com.epam.beans.INotificationBean;
import com.epam.common.DaoFactory;
import com.epam.common.InvenotyToCarUpdateConverter;
import com.epam.dao.CarUpdatesDao;
import com.epam.dao.ICarUpdatesDao;
import com.epam.errors.ApplicationException;
import com.epam.errors.DaoException;
import com.epam.models.CarUpdate;
import com.epam.models.Inventory;
import com.epam.models.Notification;
import com.epam.requests.NotificationRequest;
import com.epam.rest.clients.InventoryRestClient;
import org.bson.types.ObjectId;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 9/29/2015.
 */
@Local
public class Monitor {
    @Inject
    private INotificationBean notificationBean;

    private ICarUpdatesDao carUpdatesDao = DaoFactory.getCarUpdatesDao();
    @Inject
    private InventoryRestClient inventoryRestClient;

    public void process() throws ApplicationException {
        List<Notification> notifications = notificationBean.getAll();
//        notifications.add(getTestNotification());
        for (Notification notirfication : notifications) {
            Collection<CarUpdate> updates = findUpdate(notirfication);
            if (updates != null) {
                carUpdatesDao.insertAll(updates);
            }
        }
    }

    private Notification getTestNotification() {
        Notification notification = new Notification();
        notification.setId(new ObjectId());
        notification.setStyleId(200483764l);
        notification.setFacebookId("id");
//        notification.setPriceLowerThan(null);
//        notification.setVin(request.getVin());
//        notification.setNotificationType(request.getNotificationType());
        return notification;
    }

    Collection<CarUpdate> findUpdate(Notification notification) {
        boolean exists = existsUpdate(notification);
        if (!exists) {
            return findUpdateInSolr(notification);
        }
        return null;
    }

    private Collection<CarUpdate> findUpdateInSolr(Notification notification) {
        Collection<Inventory> inventories = inventoryRestClient.getAllInventories(notification);
        return InvenotyToCarUpdateConverter.convert(inventories, notification);
    }

    private boolean existsUpdate(Notification notification) {
        Collection<CarUpdate> updates = null;
        try {
            updates = carUpdatesDao.findUpdateByNotification(notification);
        } catch (DaoException e) {
            return false;
        }
        if (updates != null && !updates.isEmpty()) {
            return true;
        }
        return false;
    }


}
