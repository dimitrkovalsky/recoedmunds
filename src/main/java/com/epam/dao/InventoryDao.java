package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.Inventory;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import java.lang.Exception;
import java.lang.Integer;
import java.util.List;

public class InventoryDao extends BasicDAO<Inventory, Integer> implements IInventoryDao {
	public InventoryDao(Datastore datastore){
		super(datastore);
	}

	public void insert(Inventory entity) throws DaoException {
		try {
			super.save(entity);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public Inventory find(Inventory entity) throws DaoException {
		try {
			return super.findOne("_id", entity.getId());
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public List<Inventory> findAll() throws DaoException {
		try {
			return getDatastore().find(Inventory.class).asList();
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public Inventory findById(Integer id) throws DaoException {
		try {
			return super.findOne("_id", id);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public void update(Inventory entity) throws DaoException {
		try {
			super.save(entity);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}
}