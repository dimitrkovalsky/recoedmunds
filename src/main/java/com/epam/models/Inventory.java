package com.epam.models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.lang.Integer;
import java.lang.String;

@Entity(value = "inventory", noClassnameStored = true)
public class Inventory {
    @Id
    private Integer id;

    private String vin;
    private String make;
    private String model;
    private String year;

    private String bodyType;
    private String trim;

    private Long styleId;
    private String stockNumber;

    private Long dealerLocationId;

    private Long dealerId;

    private Integer mileage;

    private String listedSince;
    private String inventoryType;

    private String invoicePrice;
    private String msrpPrice;
    private String tmvInventoryPrice;
    private String dealerOfferPrice;
    private String guaranteedPrice;
    private String partnerPricePromise;

    private Double inventoryPrice;

    public Inventory() {
    }

    public String getVin() {
        return vin;
    }

    public Long getStyleId() {
        return styleId;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public String getDealerInvoice() {
        return invoicePrice;
    }

    public String getDealerMsrp() {
        return msrpPrice;
    }

    public String getTmv() {
        return tmvInventoryPrice;
    }

    public String getDealerOfferPrice() {
        return dealerOfferPrice;
    }

    public Long getDealerLocationId() {
        return dealerLocationId;
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

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(String invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String getMsrpPrice() {
        return msrpPrice;
    }

    public void setMsrpPrice(String msrpPrice) {
        this.msrpPrice = msrpPrice;
    }

    public String getTmvInventoryPrice() {
        return tmvInventoryPrice;
    }

    public void setTmvInventoryPrice(String tmvInventoryPrice) {
        this.tmvInventoryPrice = tmvInventoryPrice;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public void setDealerLocationId(Long dealerLocationId) {
        this.dealerLocationId = dealerLocationId;
    }

    public void setDealerOfferPrice(String dealerOfferPrice) {
        this.dealerOfferPrice = dealerOfferPrice;
    }

    public String getGuaranteedPrice() {
        return guaranteedPrice;
    }

    public void setGuaranteedPrice(String guaranteedPrice) {
        this.guaranteedPrice = guaranteedPrice;
    }

    public String getPartnerPricePromise() {
        return partnerPricePromise;
    }

    public void setPartnerPricePromise(String partnerPricePromise) {
        this.partnerPricePromise = partnerPricePromise;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getListedSince() {
        return listedSince;
    }

    public void setListedSince(String listedSince) {
        this.listedSince = listedSince;
    }

    public Double getInventoryPrice() {
        return inventoryPrice;
    }

    public void setInventoryPrice(Double inventoryPrice) {
        this.inventoryPrice = inventoryPrice;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }

        Inventory inventory = (Inventory) o;

        return vin.equals(inventory.vin);
    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}