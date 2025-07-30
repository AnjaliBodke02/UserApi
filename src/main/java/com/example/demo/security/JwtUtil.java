package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret ;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
//	private static final String SECRET = "ThisIsASecretKeyForJWT1234567890123456";
//	
//	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	 public String generateToken(User user) {
	        return Jwts.builder()
	                .setSubject(user.getEmail())
	                .claim("role", user.getRole().name())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	 public Claims extractClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	    }

	    public String extractUsername(String token) {
	        return extractClaims(token).getSubject();
	    }
	    public String extractRole(String token) {
	        return extractClaims(token).get("role", String.class);
	    }
	    
	    public boolean isTokenValid(String token, String username) {
	    	try {
	        String extractedUsername = extractUsername(token);
	        return extractedUsername.equals(username) && !isTokenExpired(token);
	        
	    } catch (Exception e) {
	    	return false;
	    }
	    }
	    
	    public boolean isTokenExpired(String token) {
	        return extractClaims(token).getExpiration().after(new Date());
	    }
	}
