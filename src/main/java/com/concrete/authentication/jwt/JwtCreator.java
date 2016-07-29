package com.concrete.authentication.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.concrete.authentication.domain.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class JwtCreator {

	private final String secret;
	private final Integer minutes;
	private JwtBuilder builder;
	private LocalDateTime localDateTime;
	 
	@Autowired
	public JwtCreator(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.max.time}") Integer minutes) {
		this.secret = secret;
		this.minutes = minutes;
	}

	public JwtCreator buildJwt() {
		this.builder = Jwts.builder();

		return this;
	}

	public JwtCreator createdAt(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

		this.builder.setIssuedAt(date);

		return this;
	}

	public JwtCreator ownershipFor(User user) {
		this.builder.setSubject(user.getId());

		return this;
	}

	public String thenReturn() {
		
		Instant instant = localDateTime.plusMinutes(minutes).toInstant(ZoneOffset.UTC);

		builder.setExpiration(Date.from(instant));
		
		return this.builder.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
