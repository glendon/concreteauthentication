package com.concrete.authentication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.concrete.authentication.domain.User;
import com.concrete.authentication.json.UserJson;
import com.concrete.authentication.jwt.JwtCreator;
import com.concrete.authentication.repository.UserRepository;
import com.concrete.authentication.security.UserAuthentication;

@Component
public class UserService {
	
	@Autowired
	@Qualifier("jwtCreator")
	private JwtCreator jwtCreator;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordService passwordService;

	
	public UserJson createUser(User user) {
											
		user = user.setInitialDates(new Date()).AndCryptPasswordWith(passwordService).thenSaveIn(userRepository);				
				
		if (user.getId() == null) {
			throw new RuntimeException("Ocorreu erro no cadastro");
		}

		String token = jwtCreator.buildJwt().createdAt(user.getDateCreated()).ownershipFor(user).thenReturn();
		
		return new UserJson(user, user.getDateCreated(), token);
	}
	
	public UserJson getSessionUser() {
		
		UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findOne(authentication.getId());
		
		return new UserJson(user, authentication.getDateTokenCreated(), authentication.getToken());
	}
}
