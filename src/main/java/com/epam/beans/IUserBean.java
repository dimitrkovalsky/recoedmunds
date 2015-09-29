package com.epam.beans;

import com.epam.errors.ApplicationException;
import com.epam.models.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUserBean {

	public User getUser(String id) throws ApplicationException;

	public List<User> getAllUsers() throws ApplicationException;

	public void addUser(User user) throws ApplicationException;

	public void updateUser(User user) throws ApplicationException;

	public void deleteUser(String id) throws ApplicationException;
}