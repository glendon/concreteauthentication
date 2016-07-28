package com.concrete.authentication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.concrete.authentication.domain.Phone;
import com.concrete.authentication.domain.User;
import com.concrete.authentication.validators.UserValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConcreteauthenticationApplication.class)
@WebAppConfiguration
public class UserValidatorTest {

	Validator validator = new UserValidator();
	User user;

	@Before
	public void setUp() throws Exception {
		userSetup();
	}

	public void userSetup() {
		user = new User();
		user.setName("Mock user");
		user.setEmail("mock@user.com");
		user.setPassword("123456");
		user.addPhone(new Phone(21, "98567456781"));
	}

	@Test
	public void test_supports() {
		assertTrue(validator.supports(User.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void invalid_when_name_is_null_or_empty() {

		user.setName(null);

		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());

		user.setName("");

		errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalid_when_email_is_null_or_empty() {
		user.setEmail(null);

		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());

		user.setEmail("");

		errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalid_when_password_is_null() {
		user.setPassword(null);

		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalid_when_dont_have_any_phone() {
		user.getPhones().clear();

		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void valid_when_have_all_mandatory_fields() {
		BindException errors = new BindException(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		assertTrue(!errors.hasErrors());
	}

}
