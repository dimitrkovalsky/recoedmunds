package com.epam.models;

import java.util.Collection;

/**
 * Created by Volodymyr_Kychak on 9/29/2015.
 */
public class InventoryListDto {

    private int totalCount;
    private Collection<Inventory> inventories;

    public InventoryListDto() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Collection<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Collection<Inventory> inventories) {
        this.inventories = inventories;
    }

}
