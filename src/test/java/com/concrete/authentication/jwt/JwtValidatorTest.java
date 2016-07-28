package com.concrete.authentication.jwt;

import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;

import com.concrete.authentication.ConcreteauthenticationApplication;
import com.concrete.authentication.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConcreteauthenticationApplication.class)
@WebAppConfiguration
public class JwtValidatorTest {

	public static final String SECRET_KEY = "secretkey";
	public static final Integer MINUTES_DEFAULT = 30;

	JwtValidator validator = new JwtValidator(SECRET_KEY);
	JwtCreator jwtCreator = new JwtCreator("secretkey");

	@Before
	public void setUp() throws Exception {

	}

	public User userSetup(String email) {
		User user = new User();
		user.setEmail(email);
		return user;
	}

	private String generateToken(User user, Integer minutes, Date date) {
		return jwtCreator.buildJwt().createdAt(date).expiresIn(10).ownershipFor(user).thenReturn();
	}

	@Test
	public void test_when_token_not_belongs_to_the_subject() {
		User firstUser = userSetup("user1@test.com");
		User secondUser = userSetup("user2@test.com");

		String firstUserToken = generateToken(firstUser, MINUTES_DEFAULT, new Date());

		BindException errors = new BindException(firstUser, "user");
		validator.verify(firstUserToken).andSet(errors).belongsTo(secondUser);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void test_when_token_is_expired() {

		Instant instant = LocalDateTime.now().plusMinutes(MINUTES_DEFAULT + 5).atZone(ZoneId.systemDefault())
				.toInstant();
		Date expiredDate = Date.from(instant);

		User user = userSetup("user1@test.com");
		String firstUserToken = generateToken(user, MINUTES_DEFAULT, new Date());

		BindException errors = new BindException(user, "user");
		validator.verify(firstUserToken).andSet(errors).isExpiredIn(expiredDate);

		assertTrue(errors.hasErrors());
	}

	@Test
	public void test_when_token_is_valid() {
		Date now = new Date();

		User user = userSetup("user1@test.com");
		String firstUserToken = generateToken(user, MINUTES_DEFAULT, now);

		BindException errors = new BindException(user, "user");
		validator.verify(firstUserToken).andSet(errors).belongsTo(user).isExpiredIn(now);
	}

}
