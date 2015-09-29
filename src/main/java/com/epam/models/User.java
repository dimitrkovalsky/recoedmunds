package com.epam.models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.util.ArrayList;

@Entity(value = "user", noClassnameStored = true)
public class User {
	@Id
	private String facebookId;
    private String accessToken;
	private String name;
	private String description;
	private String birthday;
	private String location;

	public ArrayList<Likes> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<Likes> likes) {
		this.likes = likes;
	}

	private ArrayList<Likes> likes;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String gender;

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}