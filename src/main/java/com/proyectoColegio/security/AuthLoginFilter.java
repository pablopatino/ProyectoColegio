package com.proyectoColegio.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoColegio.dto.LoginDTO;
import com.proyectoColegio.security.jwt.JwtProvider;
import com.proyectoColegio.security.jwt.Tokens;

public class AuthLoginFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	AuthenticationManager authenticationManager;
	
	public AuthLoginFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}

	@Autowired
	JwtProvider jwtProvider;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginDTO usuario = mapper.readValue(request.getInputStream(), LoginDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					usuario.getUsername(), usuario.getPassword());
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		Tokens tokens = jwtProvider.crearJWT(authentication);

		Map<String, String> tokensMap = new HashMap<>();
		tokensMap.put("accesToken", tokens.getAccessToken());
		tokensMap.put("refreshToken", tokens.getRefreshToken());
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), tokensMap);
	}

}
