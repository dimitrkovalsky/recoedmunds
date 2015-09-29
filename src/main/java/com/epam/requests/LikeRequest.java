package com.epam.requests;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:36
 */
public class LikeRequest {
    private String facebookId;
    private String url;
    private String location;
    private String vin;
    private String style;
    private String trim;
    private Long price;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LikeRequest{" +
                "facebookId='" + facebookId + '\'' +
                ", url='" + url + '\'' +
                ", location='" + location + '\'' +
                ", vin='" + vin + '\'' +
                ", style='" + style + '\'' +
                ", trim='" + trim + '\'' +
                ", price=" + price +
                '}';
    }
}
