package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.User;

import java.util.List;

public interface IUserDao {

	public void insert(User entity) throws DaoException;

	public User find(User entity) throws DaoException;

	public List<User> findAll() throws DaoException;

	public User findById(String id) throws DaoException;

	public void update(User entity) throws DaoException;
}