package com.concrete.authentication.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.concrete.authentication.domain.Phone;
import com.concrete.authentication.domain.User;

public class UserJson {
	
	private final String id;
	private final Date created;
	private final Date modified;
	private final Date last_login;
	private final String token;
	
	private final String name;
	private final String email;
	
	private final String password; 
	
	private final List<PhoneJson> phones = new ArrayList<>();
	
	public UserJson(User user, Date lastLogin, String token) {
		super();
		this.created = user.getDateCreated();
		this.modified = user.getDateModified();
		this.last_login = lastLogin;
		this.token = token;
		this.email = user.getEmail();
		this.name = user.getName();
		this.id = user.getId();
		
		this.password = user.getPassword();
		
		setPhones(user.getPhones());
	}

	private void setPhones(List<Phone> phones) {
		phones.forEach((phone) -> this.phones.add(new PhoneJson(phone)));
	}

	public String getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getModified() {
		return modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public List<PhoneJson> getPhones() {
		return phones;
	}

}
