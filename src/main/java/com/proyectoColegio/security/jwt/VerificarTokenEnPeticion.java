package com.proyectoColegio.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VerificarTokenEnPeticion extends OncePerRequestFilter {

	Logger logger = LoggerFactory.getLogger(VerificarTokenEnPeticion.class);

	@Autowired
	JwtProvider jwtProvider;

	public VerificarTokenEnPeticion(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
		} else {

			String header = request.getHeader("Authorization");
			if (jwtProvider.isBearer(header)) {

				try {

					String token = header.replace("Bearer ", "");

					DecodedJWT decodedJWT = jwtProvider.verificarJWT(token);

					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					Arrays.stream(roles).forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol)));

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);

				} catch (Exception e) {
					errores(e, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		}
	}

	private ObjectMapper errores(Exception e, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> errores = new HashMap<>();
		errores.put("error", e.getMessage());
		response.setContentType("application/json");
		objectMapper.writeValue(response.getOutputStream(), errores);
		return objectMapper;
	}

}
