package com.project.ecommerce.model.requests;

public class Refresh_Token_Request {
	
	
	Long id;
	String refreshToken;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	

}
