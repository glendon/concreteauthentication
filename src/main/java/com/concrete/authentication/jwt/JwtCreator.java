package com.concrete.authentication.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.concrete.authentication.domain.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JwtCreator {

	private final String secret;
	private JwtBuilder builder;
	private LocalDateTime localDateTime;

	public JwtCreator(@Value("${app.jwt.secret}") String secret) {
		this.secret = secret;
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

	public JwtCreator expiresIn(Integer minutes) {
		Instant instant = localDateTime.plusMinutes(minutes).toInstant(ZoneOffset.UTC);

		builder.setExpiration(Date.from(instant));

		return this;
	}

	public JwtCreator ownershipFor(User user) {
		this.builder.setSubject(user.getEmail());

		return this;
	}

	public String thenReturn() {
		return this.builder.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
