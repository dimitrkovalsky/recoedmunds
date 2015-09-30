package com.epam.rest.clients;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.epam.callable.CallableExecutorService;
import com.epam.callable.GetInventoryCallable;
import com.epam.common.NotificationType;
import com.epam.models.Inventory;
import com.epam.models.InventoryListDto;

import com.epam.models.Notification;
import org.apache.commons.lang3.StringUtils;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Local
public class InventoryRestClient {
    private boolean emptyFirstPage = false;
    private int pageSize = 500;

    private static final String VEHICLE_INVENTORY_PATH = "api/uinventory/getvehicleinventory";
    private static final String PURCHASE_DISCLAIMER_PATH = "api/inventory/v2/states/%s/purchasedisclaimer";
    private static final String InventoryUri = "http://qa-2-www.edmunds.com";
    //private static final String InventoryUri = "http://qa-11-www.edmunds.com";
    private static final String FRANCHISE_ID = "dealerId";
    private static final String PAGE_SIZE = "pageSize";
    private static final String INVENTORY_TYPE = "types";
    private static final String WITH_PHOTOS = "withPhotos";
    private static final String FILTER = "basicFilter";
    private static final String ADVANCED_FILTER = "advancedFilter";
    private static final String VIN_PARAM = "vin:";
    private static final String STYLE_PARAM = "style-id:";
    private static final String LOCATION_ID_PARAM = "dealer_location_id:";
    private static final String OR_SEPARATOR = " OR ";
    private static final String ANY_VALUE = "[* TO *]";
    private static final String VIEW_BASIC = "viewBasic";
    private static final String YEARS = "years";
    private static final String TYPE_PARAM_NEW = "NEW";
    private static final String TYPE_PARAM_USED = "USED:CPO";

    private static final int INVENTORY_TIMEOUT_TIME = 20; //seconds

    @Inject
    private CallableExecutorService<InventoryListDto> callableExecutorService;

    @Inject
    private Client client;

    private int numPreviousYearsNewInventory = 4;

    public void setNumPreviousYearsNewInventory(int numPreviousYearsNewInventory) {
        this.numPreviousYearsNewInventory = numPreviousYearsNewInventory;
    }

    public int getNumPreviousYearsNewInventory() {
        return numPreviousYearsNewInventory;
    }

    void setEmptyFirstPage(boolean emptyFirstPage) {
        this.emptyFirstPage = emptyFirstPage;
    }

    public boolean isEmptyFirstPage() {
        return emptyFirstPage;
    }

    void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Inventory getInventoryByVin(String vin) {
        WebTarget target = prepareWebTarget(vin);
        InventoryListDto inventoryResponse = target
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(InventoryListDto.class);
        if (inventoryResponse != null && isNotEmpty(inventoryResponse.getResultsList())) {
            return inventoryResponse.getResultsList().iterator().next();
        }
        return null;
    }

    // Collection<Inventory> getInventories(long locationId, String type, Set<Long> styleIds, Collection<Integer> years, String make, String model, String color) {
    Collection<Inventory> getInventories(Notification notification) {
        int firstPageSize = isEmptyFirstPage() ? 0 : getPageSize();
        WebTarget firstPageTarget = prepareWebTarget(notification, firstPageSize);
        WebTarget target = prepareWebTarget(notification, getPageSize());

        InventoryListDto inventoryResponse = firstPageTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(InventoryListDto.class);

        final Collection<Inventory> inventories = Sets.newHashSetWithExpectedSize(inventoryResponse.getTotalCount());
        inventories.addAll(inventoryResponse.getResultsList());

        int totalPagesRemaining = (int) Math.ceil((double) (inventoryResponse.getTotalCount() - firstPageSize) / getPageSize());
        if (totalPagesRemaining > 0) {

            final List<Callable<InventoryListDto>> jobList = Lists.newArrayList();

            for (int i = 1; i <= totalPagesRemaining; i++) {
                int page = isEmptyFirstPage() ? i : i + 1;
                jobList.add(new GetInventoryCallable(target, page));
            }

            try {
                final List<Future<InventoryListDto>> results = callableExecutorService.executeJobList(jobList);
                for (Future<InventoryListDto> result : results) {
                    if (result.get(INVENTORY_TIMEOUT_TIME, TimeUnit.SECONDS) != null) {
                        inventories.addAll(result.get().getResultsList());
                    }
                }
            } catch (Exception e) {
            }

        }

        return inventories;
    }

    private WebTarget prepareWebTarget(Notification notification, int pageSize) {
        //"http://inventorysolr.qa-2.vip.edmunds.com:8080/solr/collection1/select?q=*%3A*&fq=mappings_%7Cmake%7C%3AChrysler&fq=mappings_%7Cmodel%7C%3AAspen&wt=json&indent=true"
        WebTarget target = client

                .target(InventoryUri)
                .path(VEHICLE_INVENTORY_PATH)
                .queryParam(FRANCHISE_ID, ANY_VALUE)
                .queryParam(PAGE_SIZE, pageSize)
//                .queryParam(INVENTORY_TYPE, type)
                .queryParam(WITH_PHOTOS, false)
        //.queryParam(FILTER, LOCATION_ID_PARAM + locationId)
//                .queryParam(FILTER, STYLE_PARAM + (isNotEmpty(styleIds) ? join(styleIds, OR_SEPARATOR) : ANY_VALUE));
        .queryParam(VIEW_BASIC, "true");

//        if (notification.getStyleId() != null) {
//            target = target.queryParam(FILTER, STYLE_PARAM + notification.getStyleId());
//        }
//        if (notification.getYear() != null) {
//            target = target.queryParam(YEARS, notification.getYear());
//        }
//        if (notification.getMake() != null) {
//            target = target.queryParam("make", notification.getMake());
//        }
//        if (notification.getModel() != null) {
//            target = target.queryParam("model", notification.getModel());
//        }
//
//        if (NotificationType.PRICE_LOWER_THAN.equals(notification.getNotificationType()) && notification.getPriceLowerThan() != null) {
////            target = target.queryParam(ADVANCED_FILTER, "prices_|msrp-used-for-sorting|:[* TO "+ notification.getPriceLowerThan()+"]" );
//
//        }else if (notification.getVin() != null) {
//            target = target.queryParam(ADVANCED_FILTER, VIN_PARAM + notification.getVin());
//        }

        if(notification.getModel() == "Aspen" ){
            target = target.queryParam(ADVANCED_FILTER, "mappings_|model|:Aspen" );
        }
//
//        if (color != null) {
//            target = target.queryParam("extColors:", color);
//        }

        return target;
    }

    WebTarget prepareWebTarget(long locationId, String type, Set<Long> styleIds, Collection<Integer> years, String make, String model, String color, int pageSizeValue) {
        WebTarget target = client
                .target(InventoryUri)
                .path(VEHICLE_INVENTORY_PATH)
                .queryParam(FRANCHISE_ID, ANY_VALUE)
                .queryParam(PAGE_SIZE, pageSizeValue)
                .queryParam(INVENTORY_TYPE, type)
                .queryParam(WITH_PHOTOS, false)
                        //.queryParam(FILTER, LOCATION_ID_PARAM + locationId)
                .queryParam(FILTER, STYLE_PARAM + (isNotEmpty(styleIds) ? join(styleIds, OR_SEPARATOR) : ANY_VALUE));
        //.queryParam(VIEW_BASIC, "true");

        if (years != null) {
            String yearsParam = StringUtils.join(years, ":");
            target = target.queryParam(YEARS, yearsParam);
        }
        if (make != null) {
            target = target.queryParam("make", make);
        }
        if (model != null) {
            target = target.queryParam("model", model);
        }
        if (color != null) {
            target = target.queryParam("extColors", color);
        }


        return target;
    }

    WebTarget prepareWebTarget(String vin) {
        return client
                .target(InventoryUri)
                .path(VEHICLE_INVENTORY_PATH)
                .queryParam(FRANCHISE_ID, ANY_VALUE)
                .queryParam(WITH_PHOTOS, false)
                .queryParam(ADVANCED_FILTER, VIN_PARAM + vin)
                .queryParam(VIEW_BASIC, "true");
    }

    public Collection<Inventory> getAllInventories(Notification notification) {
        return getInventories(notification);
    }


}