package com.acintyo.project.jwtservice;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.acintyo.project.entity.UserRegistrationForm;
import com.acintyo.project.repo.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@AllArgsConstructor
public class JWTService {
	
	private final static String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";
	
	
	 private final TokenRepository tokenRepository;

	 
	public String generateJwtToken(UserRegistrationForm userRegistrationForm) {

		return Jwts.builder().subject(userRegistrationForm.getEmail()).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)).signWith(getSigninKey())
				.compact();

	}

	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }


	    public boolean isValid(String token, UserDetails user) {
	        String username = extractUsername(token);

	        boolean validToken = tokenRepository
	                .findByToken(token)
	                .map(t -> !t.isLoggedOut())
	                .orElse(false);

	        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        Claims claims = extractAllClaims(token);
	        return resolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	        return Jwts
	                .parser()
	                .verifyWith(getSigninKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

}
