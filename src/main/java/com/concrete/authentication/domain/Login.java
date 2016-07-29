package com.concrete.authentication.domain;

import com.concrete.authentication.service.PasswordService;

public class Login {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Login cryptPasswordWith(PasswordService passwordService) {
		this.password = passwordService.crypt(this.password);
		return this;
	}
	
}
