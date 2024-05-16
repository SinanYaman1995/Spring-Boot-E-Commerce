package com.project.ecommerce.service;

import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.Auth_Repository;
import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.model.requests.ResetPassword_Request;
import com.project.ecommerce.model.responses.PostResponse;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class Auth_Service {
	
	private Auth_Repository auth_repository;
	
	
	
	public Auth_Service(Auth_Repository auth_repository) {
		this.auth_repository = auth_repository;
	}
	
	public Site_User saveOneUser(Site_User newUser) {
		return auth_repository.save(newUser);
	}
	
	
	
	public Site_User getOneUserByUserName(String userId) {
		return auth_repository.findByVorname(userId);
	}
	
	
	
	public PostResponse forgot(ResetPassword_Request resetPassword_Request, PasswordEncoder passwordEncoder) {
		
	    String Email = resetPassword_Request.getEmail();
		PostResponse postResponse = new PostResponse();
		Site_User userData =  auth_repository.findByEmail(Email);
		
		
		if(userData == null) {
			postResponse.setStatus(400);
			postResponse.setMessage("There is no User with that email");
			return postResponse;
		}
		
		userData.setEmail(postResponse.getEmail());
		userData.setNachname(postResponse.getNachname());
		userData.setPhone_number(postResponse.getPhone_number());
		
		String password = new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char)i)).collect(Collectors.joining());
		userData.setPassword(passwordEncoder.encode(password));
		
		
		auth_repository.save(userData);
		
		
		SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("sinan.yaman@gmx.de");
	        message.setTo(postResponse.getEmail()); 
	        message.setSubject(password); 
	        message.setText("Password is reset");
	        //emailSender.send(message);
	       
	       postResponse.setStatus(200);
	       postResponse.setSuccess(true);
	       postResponse.setMessage("Reset Password Process Successful");
	       
		return postResponse;
	}
}
