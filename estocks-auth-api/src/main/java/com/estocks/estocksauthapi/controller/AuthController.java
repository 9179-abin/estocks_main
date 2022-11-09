package com.estocks.estocksauthapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estocks.estocksauthapi.exception.IncorrectPasswordException;
import com.estocks.estocksauthapi.exception.UserNotFoundException;
import com.estocks.estocksauthapi.service.AuthService;

@RestController
@RequestMapping("/estocks/auth/api")
public class AuthController {
	
	@Autowired
	AuthService authService;

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authentication") String authHeader) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", "");
		String token = null;
		try {
			token = authService.getUser(authHeader.trim().split(" ", 17)[1]);
		} catch (IncorrectPasswordException e) {
			throw new IncorrectPasswordException();
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException();
		}
		map.put("token", token);
		return map;
	}

}
