package com.epam.models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.lang.Integer;
import java.lang.String;

@Entity(value = "user", noClassnameStored = true)
public class User {
	@Id
	private Integer id;
	private String name;
	private String description;
	private String birthday;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}