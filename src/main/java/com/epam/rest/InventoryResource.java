package com.epam.rest;

import com.epam.beans.IInventoryBean;
import com.epam.errors.ApplicationException;
import com.epam.models.Inventory;
import com.epam.rest.clients.InventoryRestClient;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/inventories")
@Stateless
public class InventoryResource {
	@Inject
	private IInventoryBean inventoryBean;
	@Inject
	private InventoryRestClient inventoryRestClient;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Inventory> getAll() throws ApplicationException {
		Set<Long> styleIds = new HashSet<>();
		styleIds.add(200483764L);
		return inventoryRestClient.getNewInventories(1035l, styleIds);
		//return inventoryBean.getAllInventorys();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Inventory get(@PathParam("id") Integer id) throws ApplicationException {
		System.out.println("GET get : " + id);
		return inventoryBean.getInventory(id);
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String add(Inventory data) throws ApplicationException {
		System.out.println("POST add : " + data);
		inventoryBean.addInventory(data);
		return "Added Inventory";
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(Inventory data) throws ApplicationException {
		System.out.println("POST update : " + data);
		inventoryBean.updateInventory(data);
		return "Updated Inventory";
	}

	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Integer id) {
		try {
			inventoryBean.deleteInventory(id);
			return "Removed Inventory with id : " + id;
		} catch(Exception e){
			System.err.println("[InventoryResource] error");
			return "Error removing Inventory with id : " + id;
		}
	}
}