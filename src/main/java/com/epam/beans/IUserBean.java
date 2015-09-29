package com.epam.beans;

import javax.ejb.Local;
import com.epam.errors.ApplicationException;
import com.epam.models.User;
import java.util.List;
import java.lang.Integer;

@Local
public interface IUserBean {

	public User getUser(Integer id) throws ApplicationException;

	public List<User> getAllUsers() throws ApplicationException;

	public void addUser(User user) throws ApplicationException;

	public void updateUser(User user) throws ApplicationException;

	public void deleteUser(Integer id) throws ApplicationException;
}