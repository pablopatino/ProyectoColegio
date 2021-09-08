package com.proyectoColegio.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoColegio.domain.Rol;
import com.proyectoColegio.domain.Usuario;
import com.proyectoColegio.services.ServicesUsuario;

@Component
public class JwtProvider {

	Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	private static final String SECRET = "SECRETO";
	
	private final Algorithm algorithm = Algorithm.HMAC256(SECRET);
	
	@Autowired
	ServicesUsuario servicesUsuario;
	
	public Tokens crearJWT(Authentication authentication) {
	
		String accesToken = JWT.create()
				.withSubject(authentication.getName())
				.withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.withIssuer("LOGIN")
				.sign(algorithm);
		String refresToken = JWT.create()
				.withSubject(authentication.getName())
				.withExpiresAt(new Date(System.currentTimeMillis() + 100000* 60 * 1000))
				.withIssuer("LOGIN")
				.sign(algorithm);
		return new Tokens(accesToken, refresToken);
	}
	
	public Tokens crearAccesTokenDespuesDeExpirado(Usuario usuario) {
		
		String accesToken = JWT.create()
				.withSubject(usuario.getUsername())
				.withClaim("roles", usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toList()))
				.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.withIssuer("LOGIN")
				.sign(algorithm);
		return new Tokens(accesToken);
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
	
	public void refrescarTokens( HttpServletRequest request, HttpServletResponse response) throws IOException {
		String header = request.getHeader("Authorization");
		if (isBearer(header )) {
			try {
				
				String refressToken = header.replace("Bearer ", "");
				DecodedJWT decodedJWT = verificarJWT(refressToken);
				String username = decodedJWT.getSubject();
				Usuario usuario = servicesUsuario.getUser(username);
				Tokens tokens = crearAccesTokenDespuesDeExpirado(usuario);
				
				Map<String, String> tokensMap = new HashMap<>();
				tokensMap.put("accesToken", tokens.getAccessToken());
				tokensMap.put("refreshToken", refressToken);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokensMap);
				
			} catch (Exception e) {
				Map<String, String> errores = new HashMap<>();
				errores.put("error", e.getMessage());
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), errores);
			}
			
		} else {
			throw new RuntimeException("Falta refress token");
		}
	}
	
}
