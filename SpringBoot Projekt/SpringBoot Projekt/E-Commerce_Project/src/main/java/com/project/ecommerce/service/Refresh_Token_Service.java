package com.project.ecommerce.service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.RefreshTokenRepository;
import com.project.ecommerce.model.jpa.RefreshToken;
import com.project.ecommerce.model.jpa.Site_User;

@Service
public class Refresh_Token_Service {
	

	@Value("${refresh.token.expires.in}")
	private Long expireSeconds;
	
	private RefreshTokenRepository refreshTokenRepository;

	public Refresh_Token_Service(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}
	
	public String createRefreshToken(Site_User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}
	
	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserId(userId);	
	}
}