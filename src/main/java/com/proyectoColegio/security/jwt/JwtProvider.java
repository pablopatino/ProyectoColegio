package com.proyectoColegio.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtProvider {

	private static final String SECRET = "SECRETO";
	
	private final Algorithm algorithm = Algorithm.HMAC256(SECRET);
	
	public String crearJWT(Authentication authentication) {
	
		return JWT.create()
				.withSubject(authentication.getName())
				.withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.withExpiresAt(new Date(System.currentTimeMillis() * 10 + 60 + 10000))
				.withIssuer("LOGIN")
				.sign(algorithm);
	}
	
	public String crearRefressToken(Authentication authentication) {
		return JWT.create()
				.withSubject(authentication.getName())
				.withExpiresAt(new Date(System.currentTimeMillis() + 100 + 60 + 10000))
				.withIssuer("LOGIN")
				.sign(algorithm);
	}
	
	public DecodedJWT verificarJWT(String token) {
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}
	
	public boolean isBearer(String header) {
		if (header != null && header.startsWith("Bearer ")) {
			return true;
		}
		return false;
	}
	
}
