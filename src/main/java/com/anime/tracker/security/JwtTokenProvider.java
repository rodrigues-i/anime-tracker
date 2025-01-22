package com.anime.tracker.security;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private String jwtSecret = generateSecretKey();
	private Long jwtExpirationDate = 3600000L; //1h = 3600s and 3600*1000 = 3600000 milliseconds

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(expireDate)
				.signWith(key(), SignatureAlgorithm.HS256)
				.compact();
		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public  String generateSecretKey() {
		int length = 32;
		SecureRandom secureRandom = new SecureRandom();
		byte[] keyBytes = new byte[length];
		secureRandom.nextBytes(keyBytes);

		return Base64.getEncoder().encodeToString(keyBytes);
	}

	public String getUsername(String token) {
		return Jwts.parser()
					.verifyWith((SecretKey) key())
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();
	}

	public boolean validateToken(String token) {
		Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parse(token);

		return true;
	}
}