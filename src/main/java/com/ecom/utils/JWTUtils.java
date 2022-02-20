package com.ecom.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecom.exception.JWTTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtils {
	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
	@Value("jwt.secretKey")
	private String secretKey;
	private int expirationMs = 600000;
	public String generateToken(String username) {
		Date now = new Date();
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + expirationMs))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(HttpServletRequest request,String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new JWTTokenException(e.getMessage());
		}catch(MalformedJwtException e) {
			throw new JWTTokenException(e.getMessage());
		}catch(ExpiredJwtException e) {
			throw new JWTTokenException(e.getMessage());
		}catch(UnsupportedJwtException e) {
			throw new JWTTokenException(e.getMessage());
		}catch(IllegalArgumentException e) {
			throw new JWTTokenException(e.getMessage());
		}
	}
}
