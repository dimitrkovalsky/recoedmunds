package com.epam.models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "user", noClassnameStored = true)
public class User {
	@Id
	private String facebookId;
	private String name;
	private String description;
	private String birthday;
	private String location;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBirthday() {
		return birthday;
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

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}