package com.project.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.model.requests.LoginRequest;
import com.project.ecommerce.model.requests.QuestionRequest;
import com.project.ecommerce.model.requests.RegisterRequest;
import com.project.ecommerce.model.requests.UserRequest;
import com.project.ecommerce.model.responses.AuthResponse;
import com.project.ecommerce.model.responses.PostResponse;
import com.project.ecommerce.model.responses.Site_User_Response;
import com.project.ecommerce.service.Site_User_Service;



@RestController
@ComponentScan({"com.project.ecommerce.service"})
@RequestMapping(path = "/site_user")
public class Site_User_Controller {
	
	private Site_User_Service site_User_Service;
	
	public Site_User_Controller(Site_User_Service site_User_Service) {
		this.site_User_Service = site_User_Service;
	}
	
	
	@GetMapping
	public List<Site_User_Response> getAllUsers(){
		return site_User_Service.getAllUsers().stream().map(u -> new Site_User_Response(u)).toList();
	}



@ResponseBody
@PostMapping("/Edit_User/{edit_kind}")
public AuthResponse edit_User(@PathVariable String edit_kind, @RequestBody UserRequest userRequest) throws AccountNotFoundException {
	 
	 Long id = userRequest.getId();
	 System.out.println(id);
	 
	 Site_User userDataForRegister = new Site_User();
	 
	 UserRequest userdata = null;
	 if(id == null){
		 throw new AccountNotFoundException();
	 }
	 
	 AuthResponse authResponse = new AuthResponse();
	
	 if(edit_kind.equals("update_User")) {
		
			String vorname = userRequest.getVorname() != null ? userRequest.getVorname() : userDataForRegister.getVorname();
			String nachname = userRequest.getNachname() != null ? userRequest.getNachname() : userDataForRegister.getNachname();
			String email = userRequest.getEmail() != null ? userRequest.getEmail() : userDataForRegister.getNachname();
			String phone_number = userRequest.getPhone_number() != null ? userRequest.getPhone_number() : userDataForRegister.getPhone_number();
			String password = userRequest.getPassword() != null ? userRequest.getPassword() : userDataForRegister.getPassword() ;
			
			System.out.println(vorname + " " + nachname+ " " + email+ " " + phone_number+ " " + password);
			
			userDataForRegister = site_User_Service.loadUserById(id);
			
			userDataForRegister.setVorname(vorname);
			userDataForRegister.setNachname(nachname);
			userDataForRegister.setEmail(email);
			userDataForRegister.setPhone_number(phone_number);
			userDataForRegister.setPassword(password);
			
			userDataForRegister = site_User_Service.updateOneUser(id, userDataForRegister);
					
			authResponse.setData(userdata);
			authResponse.setStatus(200);
		 	
			return authResponse;
		 
	 }else if(edit_kind.equals("delete_User")) {
		 site_User_Service.deleteById(id);
	 }
	 
	 return authResponse;
	}


	@PutMapping("/{Id}")
	public ResponseEntity<Void> updateOneUser(@PathVariable Long Id, @RequestBody Site_User newUser) {
		Site_User user = site_User_Service.updateOneUser(Id, newUser);
		if(user != null) 
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	
	@DeleteMapping("/{userId}")
	 public void deleteOneUser(@PathVariable Long userId) {
		site_User_Service.deleteById(userId);
	}
}