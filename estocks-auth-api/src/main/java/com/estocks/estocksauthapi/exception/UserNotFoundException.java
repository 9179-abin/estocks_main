package com.estocks.estocksauthapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not available in our records. Please try with valid input.")

public class UserNotFoundException extends Exception {
	
	public UserNotFoundException() {
		super();
	}

}
