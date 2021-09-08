package com.proyectoColegio.security.jwt;

public class Tokens {

	private String accessToken;
	private String refreshToken;
	public Tokens(String accessToken, String refreshToken) {

		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	
	public Tokens(String accessToken) {
		this.accessToken = accessToken;
	}


	public Tokens() {

	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	
	
	
}
