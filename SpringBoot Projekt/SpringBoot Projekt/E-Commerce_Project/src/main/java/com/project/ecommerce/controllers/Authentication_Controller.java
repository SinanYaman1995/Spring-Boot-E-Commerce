package com.project.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.jpa.RefreshToken;
import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.model.requests.LoginRequest;
import com.project.ecommerce.model.requests.Refresh_Token_Request;
import com.project.ecommerce.model.requests.RegisterRequest;
import com.project.ecommerce.model.requests.ResetPassword_Request;
import com.project.ecommerce.model.responses.AuthResponse;
import com.project.ecommerce.model.responses.PostResponse;
import com.project.ecommerce.security.JwtTokenProvider;
import com.project.ecommerce.service.Auth_Service;
import com.project.ecommerce.service.Refresh_Token_Service;




@RestController
@ComponentScan({"com.project.ecommerce.service"})
@ComponentScan({"com.project.ecommerce.config"})
@RequestMapping(path = "/auth")
public class Authentication_Controller {

	@Autowired
	private Auth_Service authService;
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private PasswordEncoder passwordEncoder;

	private Refresh_Token_Service refreshTokenService;
	

	
	public Authentication_Controller(AuthenticationManager authenticationManager, Auth_Service authService, 
    		PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, Refresh_Token_Service refreshTokenService) {
		
			this.authService = authService;
			this.authenticationManager = authenticationManager;
			this.jwtTokenProvider = jwtTokenProvider;
			this.passwordEncoder = passwordEncoder;
			this.refreshTokenService = refreshTokenService;
	}
	

	 
	 @ResponseBody
	 @PostMapping("/login")
	 public AuthResponse login(@RequestBody LoginRequest loginRequest) {
		 System.out.println("tetsatdsatsadttdsat");
		 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		 Authentication auth = this.authenticationManager.authenticate(authToken);
		 SecurityContextHolder.getContext().setAuthentication(auth);
		 String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		 Site_User user = authService.getOneUserByUserName(loginRequest.getUserName());
		 AuthResponse authResponse = new AuthResponse();
		 authResponse.setAccessToken("Bearer " + jwtToken);
		 authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		 authResponse.setUserId(user.getId());
		 
		 return authResponse;
		 
	 }


		@PostMapping("/register")
		public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
			AuthResponse authResponse = new AuthResponse();
			
			if(authService.getOneUserByUserName(registerRequest.getVorname()) != null) {
				authResponse.setMessage("Username already in use.");
				return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
			}
			
			
			Site_User user = new Site_User();
			user.setVorname(registerRequest.getVorname());
			user.setNachname(registerRequest.getNachname());
			user.setEmail(registerRequest.getEmail());
			user.setPhone_number(registerRequest.getPhone_number());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			
			authService.saveOneUser(user);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getVorname(), registerRequest.getPassword());
			Authentication auth = this.authenticationManager.authenticate(authToken);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwtToken = jwtTokenProvider.generateJwtToken(auth);
			authResponse.setMessage("User successfully registered.");
			authResponse.setAccessToken("Bearer " + jwtToken);
			authResponse.setUserId(user.getId());
		
			return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
		}
	 
	 
	 
		 @ResponseBody
		 @PostMapping("/ForgotPassword")
		 public PostResponse ForgotPassword(@RequestBody ResetPassword_Request resetPassword_Request) {
			 
			 PostResponse postResponse =  authService.forgot(resetPassword_Request, passwordEncoder);
		
			 return postResponse;
		 }
		 
		 
		 
		 
		 
		@PostMapping("/refresh")
		public ResponseEntity<AuthResponse> refresh(@RequestBody Refresh_Token_Request refreshRequest) {
			AuthResponse response = new AuthResponse();
			RefreshToken token = refreshTokenService.getByUser(refreshRequest.getId());
			if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
					!refreshTokenService.isRefreshExpired(token)) {

				Site_User user = token.getUser();
				String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
				response.setMessage("token successfully refreshed.");
				response.setAccessToken("Bearer " + jwtToken);
				response.setUserId(user.getId());
				return new ResponseEntity<>(response, HttpStatus.OK);		
			} else {
				response.setMessage("refresh token is not valid.");
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
	}
}