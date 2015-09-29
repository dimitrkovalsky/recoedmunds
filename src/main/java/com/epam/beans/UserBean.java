package com.epam.beans;

import com.epam.common.DaoFactory;
import com.epam.dao.IUserDao;
import com.epam.errors.ApplicationException;
import com.epam.models.User;
import java.lang.Integer;
import java.util.List;

public class UserBean implements IUserBean {
	public User getUser(String id) throws ApplicationException {
		return DaoFactory.getUserDao().findById(id);
	}

	public List<User> getAllUsers() throws ApplicationException {
		return DaoFactory.getUserDao().findAll();
	}

	public void addUser(User user) throws ApplicationException {
		IUserDao dao = DaoFactory.getUserDao();
		dao.insert(user);
	}

	public void updateUser(User user) throws ApplicationException {
		IUserDao dao = DaoFactory.getUserDao();
		dao.update(user);
	}

	public void deleteUser(String id) throws ApplicationException {
		IUserDao dao = DaoFactory.getUserDao();
		User user = new User();
		user.setFacebookId(id);
	}
}