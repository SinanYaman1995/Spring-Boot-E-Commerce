package com.project.ecommerce.model.responses;

import com.project.ecommerce.model.jpa.Site_User;

public class AuthResponse {
	
	String message;
	Long userId;
	String accessToken;
	String refreshToken;
	int status;
	String email;
	Object data;
	
	public AuthResponse() {}
	
	
	public AuthResponse(Site_User user) {
		userId = user.getId();
		email = user.getEmail();
	}
	
	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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