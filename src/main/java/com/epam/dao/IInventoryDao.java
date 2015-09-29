package com.epam.dao;

import java.util.List;
import java.lang.Integer;
import com.epam.models.Inventory;
import com.epam.errors.DaoException;

public interface IInventoryDao {

	public void insert(Inventory entity) throws DaoException;

	public Inventory find(Inventory entity) throws DaoException;

	public List<Inventory> findAll() throws DaoException;

	public Inventory findById(Integer id) throws DaoException;

	public void update(Inventory entity) throws DaoException;
}