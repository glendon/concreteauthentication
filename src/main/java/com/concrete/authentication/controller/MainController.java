package com.concrete.authentication.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.authentication.domain.Login;
import com.concrete.authentication.json.UserJson;
import com.concrete.authentication.service.LoginService;

@RestController
public class MainController {
	
	@Autowired	
	private LoginService loginService;
	
	@RequestMapping(path = "/login", method = POST)
	public @ResponseBody ResponseEntity<UserJson> login(@RequestBody Login login) {
		
		UserJson userJson = loginService.doLogin(login);
		
		return new ResponseEntity<UserJson>(userJson, HttpStatus.OK);
	}

}
