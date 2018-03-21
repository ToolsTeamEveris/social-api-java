package com.social.helper;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.social.entity.Person;
import com.social.manager.PersonManager;

@Service
public class AuthService {
	
	private final PersonManager personManager;

	@Autowired
	public AuthService (final PersonManager personManager) {
		this.personManager = personManager;
	}
	
	public String createToken(Long id) {
		try {
			Calendar c = Calendar.getInstance();
			Date now = c.getTime();
			c.add(Calendar.MINUTE, 60);
			Date expirationDate = c.getTime();
			
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		        .withIssuer("auth0")
		        .withClaim("id", id)
		        .withIssuedAt(now)
		        .withNotBefore(now)
		        .withExpiresAt(expirationDate)
		        .sign(algorithm);
		    return token;
		} catch (UnsupportedEncodingException exception) {
		    System.out.println(exception);
		} catch (JWTCreationException exception) {
		    System.out.println(exception);
		}
		return null;
	}
	
	public Person verifyToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);

		    final Long userId = jwt.getClaim("id").asLong();
		    
		    return personManager.findById(userId);

		} catch (UnsupportedEncodingException exception) {
		    System.out.println(exception);
		} catch (JWTVerificationException exception) {
		    System.out.println(exception);
		}
		return null;
	}
}
