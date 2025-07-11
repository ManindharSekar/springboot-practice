package com.example.spring_security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.spring_security.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String secretKey=null;

	public String generateToken(User user) {
		// TODO Auto-generated method stub
		HashMap<String, Object> claims = new HashMap<String, Object>();
		return Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(user.getUserName())
				.issuer("Mani")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*10*1000))
				.and()
				.signWith(generateKey())
				.compact();
		
				
		
	}
	
	private SecretKey generateKey() {
		// TODO Auto-generated method stub
		byte[] decode = Decoders.BASE64.decode(getSecretKey());
		return Keys.hmacShaKeyFor(decode);
	}

	public String getSecretKey() {
		return "1K3rz7k0jg1sITXtY3l6eYEmESV14DkfPTv1Y+vl0rc=";
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
		// TODO Auto-generated method stu
		Claims claims=extractClaims(token);
		return claimResolver.apply(claims);
	}




	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		
		return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
		
	}

	public boolean isTokenValid(String token, UserDetails userdetails) {
		// TODO Auto-generated method stub
		String userName = extractUserName(token);
		return (userName.equals(userdetails.getUsername())&&isTokenExpire(token));
	}

	private boolean isTokenExpire(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getExpiration);
	}

}
