package com.concrete.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {

	private final String salt;
	
	@Autowired
	public PasswordService(@Value("${app.salt}") String salt) {
		this.salt = salt;
	}
	
	public String crypt(String password) {
		return BCrypt.hashpw(password, salt);
	}
	
}
