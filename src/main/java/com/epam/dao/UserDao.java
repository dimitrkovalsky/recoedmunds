package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.User;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

import java.util.List;

public class UserDao extends BasicDAO<User, String> implements IUserDao {
	public UserDao(Datastore datastore){
		super(datastore);
	}

	public void insert(User entity) throws DaoException {
		try {
			super.save(entity);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public User find(User entity) throws DaoException {
		try {
			return super.findOne("_id", entity.getFacebookId());
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public List<User> findAll() throws DaoException {
		try {
			return getDatastore().find(User.class).asList();
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public User findById(String id) throws DaoException {
		try {
			return super.findOne("_id", id);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}

	public void update(User entity) throws DaoException {
		try {
			super.save(entity);
		} catch(Exception e){
			throw new DaoException(e);
		}
	}


}