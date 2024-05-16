package com.project.ecommerce.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.service.Site_User_Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class JwtTokenProvider {
	
	@Value("{ecommerce.app.secret}")
	private String APP_SECRET;
	
	@Value("${ecommerce.expires.in}")
	private long EXPIRES_IN;
	
	
	Site_User_Service site_User_Service;
	
	
	
	public JwtTokenProvider(Site_User_Service site_User_Service) {
		this.site_User_Service = site_User_Service;
	}
	
	
	public String generateJwtToken(Authentication auth) {
		
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		Site_User us = site_User_Service.loadUserByUsername(username);
		String id = Long.toString(us.getId());
	
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(id.toString())
				.setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	
	
	
	
	String getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	
	public String generateJwtTokenByUserId(Long userId) {
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userId))
				.setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	
	
	boolean validateToken(String token) {
		try {
			System.out.println("validateToken");
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	
	
	private boolean isTokenExpired(String token) {
		System.out.println("osTokenExpired");
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
		
	}
}
