package com.epam.beans;

import com.epam.common.DaoFactory;
import com.epam.dao.IInventoryDao;
import com.epam.errors.ApplicationException;
import com.epam.models.Inventory;
import java.lang.Integer;
import java.util.List;

public class InventoryBean implements IInventoryBean {
	public Inventory getInventory(Integer id) throws ApplicationException {
		return DaoFactory.getInventoryDao().findById(id);
	}

	public List<Inventory> getAllInventorys() throws ApplicationException {
		return DaoFactory.getInventoryDao().findAll();
	}

	public void addInventory(Inventory inventory) throws ApplicationException {
		IInventoryDao dao = DaoFactory.getInventoryDao();
		dao.insert(inventory);
	}

	public void updateInventory(Inventory inventory) throws ApplicationException {
		IInventoryDao dao = DaoFactory.getInventoryDao();
		dao.update(inventory);
	}

	public void deleteInventory(Integer id) throws ApplicationException {
		IInventoryDao dao = DaoFactory.getInventoryDao();
		Inventory inventory = new Inventory();
		inventory.setId(id);
	}
}