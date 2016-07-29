package com.concrete.authentication.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.authentication.domain.User;
import com.concrete.authentication.json.UserJson;
import com.concrete.authentication.json.ValidationErrorJson;
import com.concrete.authentication.service.MessageResolver;
import com.concrete.authentication.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private MessageResolver messageResolver;

	@Autowired
	private UserService userService;
	
	@Autowired	
	private Validator userValidator;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<ValidationErrorJson> exceptionHandler(MethodArgumentNotValidException ex) {
        
        ValidationErrorJson processed = ValidationErrorJson.processFieldErrors(ex.getBindingResult().getFieldErrors(), messageResolver);        
        
        return new ResponseEntity<ValidationErrorJson>(processed, HttpStatus.BAD_REQUEST);
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

	@RequestMapping(path = "/user/{id}", method = GET)
	public @ResponseBody ResponseEntity<UserJson> show(@PathVariable String id) {	

		UserJson userJson = userService.getUser(id);

		if (userJson != null) {
			return new ResponseEntity<UserJson>(userJson, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserJson>(userJson, HttpStatus.NOT_FOUND);
		}
	}
	
}
