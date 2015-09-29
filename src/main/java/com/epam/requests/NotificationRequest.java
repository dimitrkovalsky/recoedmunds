package com.epam.requests;

import com.epam.common.NotificationType;

import javax.ejb.Local;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 10:47
 */
public class NotificationRequest {
    private NotificationType notificationType;
    private String facebookId;
    private Long priceLowerThan;
    private String vin;
    private Long styleId;
    private String make;
    private String model;
    private String year;

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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }
}

