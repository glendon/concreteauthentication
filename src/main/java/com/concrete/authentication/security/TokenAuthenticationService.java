package com.concrete.authentication.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import com.concrete.authentication.domain.User;
import com.concrete.authentication.jwt.JwtValidator;
import com.concrete.authentication.repository.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class TokenAuthenticationService {

	    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	    
	    @Autowired
	    private JwtValidator tokenHandler;
	    
	    @Autowired
		private UserRepository userRepository;
	  

	    public Authentication getAuthentication(HttpServletRequest request) {
	        final String token = request.getHeader(AUTH_HEADER_NAME);
	        
	        if (token != null) {
	        	 BindException errors = new BindException(token, "token");
	        	 
	             Claims claims = tokenHandler.verify(token).andSet(errors).isExpiredIn(new Date()).thenReturnClaims();
	             User user = userRepository.findOne(claims.getSubject());	             
	            if (user != null && !errors.hasErrors()) {
	         
	                return new UserAuthentication(user, token, claims.getIssuedAt());
	            }
	        }
	        return null;
	    }
	}
