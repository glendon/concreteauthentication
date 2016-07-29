package com.concrete.authentication.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concrete.authentication.controller.exception.InvalidLoginException;
import com.concrete.authentication.domain.Login;
import com.concrete.authentication.domain.User;
import com.concrete.authentication.json.UserJson;
import com.concrete.authentication.jwt.JwtCreator;
import com.concrete.authentication.repository.UserRepository;

@Component
public class LoginService {
	
	@Autowired
	private JwtCreator jwtCreator;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	public UserJson doLogin(Login login) throws InvalidLoginException {
						
		List<User> users = userRepository.findByEmailAndPassword(login.getEmail(), login.cryptPasswordWith(passwordService).getPassword());
		if (users == null || users.isEmpty()){
			throw new InvalidLoginException();
		}
	
		User user = users.get(0);
		
		Date now = new Date();
		String token = jwtCreator.buildJwt().createdAt(now).ownershipFor(user).thenReturn();
		
		return new UserJson(user, now, token);
	} 

}
