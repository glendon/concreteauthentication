package com.concrete.authentication.jwt;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.concrete.authentication.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private final String secret;

	private Claims claims;
	private Errors errors;

	@Autowired
	public JwtValidator(@Value("${app.jwt.secret}") String secret) {
		this.secret = secret;
	}

	public JwtValidator verify(String jwt) {

		if (jwt == null) {
			throw new IllegalArgumentException("JWT can't be null");
		}

		claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(this.secret)).parseClaimsJws(jwt)
				.getBody();

		return this;
	}

	public JwtValidator andSet(Errors errors) {
		if (errors == null) {
			throw new IllegalArgumentException("Errors can't be null");
		}

		this.errors = errors;

		return this;
	}

	public JwtValidator belongsTo(User user) {

		if (user == null) {
			throw new IllegalArgumentException("User can't be null");
		}

		if (!validSubject(user)) {
			errors.reject("msg.auth.not.authorized");
		}

		return this;
	}

	public JwtValidator isExpiredIn(Date date) {
		Date expirationTime = claims.getExpiration();

		if (expirationTime != null) {
			if (expirationTime.getTime() < date.getTime()) {
				errors.reject("msg.auth.token.authorized");
			}
		}
		
		return this;
	}
	
	public Claims thenReturnClaims(){
		if (!errors.hasErrors()){
			return claims;
		}
		return null;		
	}

	private boolean validSubject(User user) {
		return claims.getSubject().equals(user.getId());
	}

}
