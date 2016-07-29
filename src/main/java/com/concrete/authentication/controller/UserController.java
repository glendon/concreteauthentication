package com.concrete.authentication.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.authentication.domain.Login;
import com.concrete.authentication.domain.User;
import com.concrete.authentication.json.UserJson;
import com.concrete.authentication.json.ValidationErrorJson;
import com.concrete.authentication.service.MessageResolver;
import com.concrete.authentication.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	@Qualifier("messageResolver")
	private MessageResolver messageResolver;

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userValidator")
	private Validator userValidator;
			
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ResponseBody
    public @ResponseBody ValidationErrorJson processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return ValidationErrorJson.processFieldErrors(fieldErrors, messageResolver);
    }

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(path = "/user", method = POST)
	public @ResponseBody ResponseEntity<UserJson> save(@RequestBody @Validated User user) {
		
		UserJson userJson = userService.createUser(user);
		
		return new ResponseEntity<UserJson>(userJson, HttpStatus.OK);
	}

	@RequestMapping(path = "/user", method = GET)
	public @ResponseBody ResponseEntity<UserJson> show() {	

		UserJson userJson = userService.getSessionUser();

		return new ResponseEntity<UserJson>(userJson, HttpStatus.OK);
	}
	
}
