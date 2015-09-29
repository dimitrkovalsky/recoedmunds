package com.epam.common;

import com.epam.models.CarUpdate;
import com.epam.models.Inventory;
import com.epam.models.Notification;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by Volodymyr_Kychak on 9/29/2015.
 */
public class InvenotyToCarUpdateConverter {
    public static Collection<CarUpdate> convert(Collection<Inventory> inventories, Notification notification) {
        Set<CarUpdate> updates = newHashSet();
        for (Inventory inventory : inventories) {
            updates.add(convert(inventory, notification));
        }
        return updates;
    }

    public static CarUpdate convert(Inventory inventory, Notification notification) {
        CarUpdate update = new CarUpdate();
        update.setTrim(inventory.getTrim());
        update.setBodyType(inventory.getBodyType());
        update.setMake(inventory.getMake());
        update.setModel(inventory.getModel());
        update.setMsrpPrice(inventory.getMsrpPrice());
        update.setStyleId(inventory.getStyleId());
        update.setVin(inventory.getVin());
        update.setYear(inventory.getYear());
        update.setFacebookId(notification.getFacebookId());
        update.setId(new ObjectId());
        return update;

    }
}
