package com.epam.models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.lang.Integer;
import java.lang.String;

@Entity(value = "inventory", noClassnameStored = true)
public class Inventory {
	@Id
	private Integer id;
	private String description;

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}