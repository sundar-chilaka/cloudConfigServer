package com.sundar.JWT;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${spring.app.jwtSecret}")
	private String jwtSecret;

	@Value("${spring.app.jwtExpirationMs}")
	private String jwtExpirationMs;

	// getting Bearer Header "Authorization"
	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		logger.debug("Authorization Header: {}" + bearerToken);
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
		    String token = bearerToken.substring(7);// Remove Bearer Prefix
		}
		return null;
	}

	// generateTokenFromUserName
	public String generateTokenFromUserName(UserDetails userDetails) {
		String userName = userDetails.getUsername();
		return Jwts.builder().subject(userName).issuedAt(new Date())
				.expiration(new Date((new Date().getTime() + jwtExpirationMs))).signWith(Key()).compact();
	}

//decode for the Key
	public Key Key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// getUserNameFromJwtToken
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().verifyWith((SecretKey) Key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			logger.info("validateing Token");
			Jwts.parser().verifyWith((SecretKey) Key()).build().parseSignedClaims(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid Jwt Token : {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Jwt Token is expired : {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("Jwt Token is UnSupported : {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Jwt claims String is Empty: {}");
		}
		return false;
	}

}
