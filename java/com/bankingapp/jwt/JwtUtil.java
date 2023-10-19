package com.bankingapp.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${jwt.expiration}")
	private long auth_Exp_Time;
	
	@Value("${jwt.secret}")
	private String auth_key;

	public String generateToken(String userName) {
		HashMap<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, userName);
	}

	private String createToken(HashMap<String, Object> claims, String userName) {
		Date todayDate = new Date();
		Date expirationTime = new Date(todayDate.getTime() + auth_Exp_Time);
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(todayDate)
				.setExpiration(expirationTime).signWith(SignatureAlgorithm.HS512, auth_key).compact();
	}

	public String getUserName(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}

	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(auth_key).parseClaimsJws(token).getBody();
	}

	public Date getExpirationTime(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);

	}

	public boolean isTokenExpired(String token) {
		Date date = getExpirationTime(token);
		return date.before(new Date());
	}

	public boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}
