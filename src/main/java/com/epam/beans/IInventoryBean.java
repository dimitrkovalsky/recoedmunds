package com.epam.beans;

import javax.ejb.Local;
import com.epam.errors.ApplicationException;
import com.epam.models.Inventory;
import java.util.List;
import java.lang.Integer;

@Local
public interface IInventoryBean {

	public Inventory getInventory(Integer id) throws ApplicationException;

	public List<Inventory> getAllInventorys() throws ApplicationException;

	public void addInventory(Inventory inventory) throws ApplicationException;

	public void updateInventory(Inventory inventory) throws ApplicationException;

	public void deleteInventory(Integer id) throws ApplicationException;
}