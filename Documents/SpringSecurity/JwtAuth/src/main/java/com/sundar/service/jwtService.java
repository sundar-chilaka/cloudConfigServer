package com.sundar.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;

@Service
public class jwtService {

	public String genarateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,userName);
	}

	private  String createToken(Map<String, Object> claims, String userName) throws JwtException {
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(userName)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				   .signWith(getSecKey(),SignatureAlgorithm.ES256).compact();
	}

	private Key getSecKey() {
		// TODO Auto-generated method stub
		return null;
	}
}

