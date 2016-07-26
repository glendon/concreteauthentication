package com.concrete.authentication.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.validation.Errors;

public class User {
	
	@Transient
	Errors errors;
	
	private String name;

	private String email;

	private String password;

	private List<Phone> phones = new ArrayList<>();

	public void setName(String name) {
		this.name = name;		
	}
	
	public String getName(){
		return this.name;
	}

	public void setEmail(String email) {
		this.email = email;		
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;		
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}
	
	public void setPhone(List<Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone phone) {
		if (this.phones == null) {
			this.phones = new ArrayList<>();
		}
		
		this.phones.add(phone);
		
	}

	public User WhenIsNotNotValidSet(Errors errors) {
		this.errors = errors;
		return this;
	}

	public User verifyName() {
		if (this.name == null || this.name.isEmpty()) {
			errors.rejectValue("name", "msg.user.name.mandatory");
		}
		return this;
	}

	public User verifyEmail() {
		if (this.email == null || this.email.isEmpty()) {
			errors.rejectValue("email", "msg.user.email.mandatory");
		}
		return this;		
	}
	
	public User verifyPassword() {
		if (this.password == null){
			errors.rejectValue("password", "msg.user.password.mandatory");
		}
		return this;
	}

	public User verifyPhones() {
		if (this.phones == null || this.phones.isEmpty()){
			errors.rejectValue("phones", "msg.user.phones.mandatory");
		}
		
		return this;		
	}

	


}
