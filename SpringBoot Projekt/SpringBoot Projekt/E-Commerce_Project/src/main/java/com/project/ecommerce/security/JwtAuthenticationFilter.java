package com.project.ecommerce.security;

import java.io.IOException;

/*
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.service.Site_User_Service;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	Site_User_Service site_User_Service;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwtToken = extractJwtFromRequest(request);

			System.out.println(StringUtils.hasText(jwtToken) + " " + jwtTokenProvider.validateToken(jwtToken));
			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
				String id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
				System.out.println("Vor der Datenbankanfrage " + id);
				Long id2 =   Long.parseLong(id.toString());
				Site_User user = site_User_Service.loadUserById(id2);
				System.out.println("User in authentication " + user);
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					System.out.println("SecurityContextHolder");
					/*hält daten com user in einem Storage nicht sicher*/
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch(Exception e) {
			return;
		}
		System.out.println("doFilter");
		filterChain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		System.out.println("extractJwtFromRequest1");
		System.out.println(request.getHeader("Authorization"));
		
		String bearer = request.getHeader("Authorization");
		System.out.println("extractJwtFromRequest2");
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
			return bearer.substring("Bearer".length() + 1);
		return null;
	}
}