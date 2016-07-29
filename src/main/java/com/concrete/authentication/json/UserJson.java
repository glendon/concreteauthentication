package com.concrete.authentication.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.concrete.authentication.domain.Phone;
import com.concrete.authentication.domain.User;
import com.concrete.authentication.utils.DateUtils;

public class UserJson {
	
	private final String id;
	private final String created;
	private final String modified;
	private final String last_login;
	private final String token;
	
	private final String name;
	private final String email;
	
	private final String password; 
	
	private final List<PhoneJson> phones = new ArrayList<>();
	
	public UserJson(User user, Date lastLogin, String token) {
		super();
		this.created = DateUtils.formatDate(user.getDateCreated());
		this.modified = DateUtils.formatDate(user.getDateModified());
		this.last_login = DateUtils.formatDate(lastLogin);
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

	public String getCreated() {
		return created;
	}

	public String getModified() {
		return modified;
	}

	public String getLast_login() {
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
