package com.estocks.estockscloudgateway.config;

import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	private Key key;
	
	private boolean isExpired;
	private boolean isInvalid;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.isExpired = false;
		this.isInvalid = false;
	}

	public Claims getAllClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			this.isExpired = false;
			this.isInvalid = false;
		} catch (ExpiredJwtException e) {
			this.isExpired = true;
		} catch (Exception e) {
			this.isInvalid = true;
		}
		return claims;
	}

	public boolean isTokenExpired(String token) {
		return this.isExpired;
	}

	public boolean isInvalid(String token) {
		this.getAllClaimsFromToken(token);
		return this.isInvalid;
	}
	
}
