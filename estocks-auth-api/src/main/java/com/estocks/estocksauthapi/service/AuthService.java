package com.estocks.estocksauthapi.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estocks.estocksauthapi.config.JwtUtil;
import com.estocks.estocksauthapi.exception.IncorrectPasswordException;
import com.estocks.estocksauthapi.exception.UserNotFoundException;
import com.estocks.estocksauthapi.repository.User;
import com.estocks.estocksauthapi.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;
	private final JwtUtil jwt;
	
	public AuthService(UserRepository userRepository, final JwtUtil jwt) {
		super();
		this.userRepository = userRepository;
		this.jwt = jwt;
	}
	
	public String getUser(String authHeader) throws Exception  {
		byte[] codes = Base64.getDecoder().decode(authHeader);
		String userData = new String(codes);
		String username = userData.split(":")[0];
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			if (userData.split(":")[1].equals(user.getPassword())) {
				return jwt.generate(user);
			} else {
				throw new IncorrectPasswordException();
			}
		}
	}

}
