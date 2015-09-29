package com.epam.rest;

import com.epam.beans.IUserBean;
import com.epam.errors.ApplicationException;
import com.epam.models.User;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Stateless
public class UserResource {
	@Inject
	private IUserBean userBean;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() throws ApplicationException {
		return userBean.getAllUsers();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("id") Integer id) throws ApplicationException {
		System.out.println("GET get : " + id);
		return userBean.getUser(id);
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(User data) throws ApplicationException {
		System.out.println("POST add : " + data);
		userBean.addUser(data);
		return "Added User";
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(User data) throws ApplicationException {
		System.out.println("POST update : " + data);
		userBean.updateUser(data);
		return "Updated User";
	}

	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Integer id) {
		try {
			userBean.deleteUser(id);
			return "Removed User with id : " + id;
		} catch(Exception e){
			System.err.println("[UserResource] error");
			return "Error removing User with id : " + id;
		}
	}
}