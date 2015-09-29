package com.epam.models;

import com.epam.common.NotificationType;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 11:01
 */
@Entity(value = "notification", noClassnameStored = true)
public class Notification {
    @Id
    private ObjectId id;
    private String facebookId;
    private Long priceLowerThan;
    private String location;
    private String vin;
    private Long styleId;
    private String make;
    private String model;
    private String year;
    private NotificationType  notificationType;

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Long getPriceLowerThan() {
        return priceLowerThan;
    }

    public void setPriceLowerThan(Long priceLowerThan) {
        this.priceLowerThan = priceLowerThan;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
