package com.concrete.authentication.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.concrete.authentication.domain.User;
import com.concrete.authentication.repository.UserRepository;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

        if (user == null) {  
            errors.rejectValue("city", "msg.user.mandatory");
        } else {
            user.WhenIsNotNotValidSet(errors).verifyName().verifyEmail().isEmailUniqueIn(userRepository).verifyPassword().verifyPhones();
        }

	}

}
