package com.concrete.authentication.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Usuário e/ou senha inválidos")
public class InvalidLoginException extends Exception {
	private static final long serialVersionUID = 100L;
}
