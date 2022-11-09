package com.estocks.estocksauthapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Password entered does not match with our records.")

public class IncorrectPasswordException extends Exception {

	public IncorrectPasswordException() {
		super();
	}
	
}