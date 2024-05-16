package com.project.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.ecommerce.dao.UserRepository;
import com.project.ecommerce.model.jpa.Site_User;
import com.project.ecommerce.model.responses.Site_User_Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Site_User_Service {
	
	private UserRepository  user_Repository;
	
	public Site_User_Service(UserRepository  user_Repository) {
		this.user_Repository = user_Repository;
	}
	
	public List<Site_User> getAllUsers() {
		return user_Repository.findAll();
	}
	
	public Optional<Site_User> getOneUserById(Long id) {
		return user_Repository.findById(id);
	}
	
	public Site_User getOneUserByUserName(String userName) {
		System.out.println(userName);
		return user_Repository.findByVorname(userName);
	}

	public Site_User saveOneUser(Site_User userDataForRegister) {
		return user_Repository.save(userDataForRegister);
	}	
	
	public Site_User loadUserByUsername(String username) throws UsernameNotFoundException {
		Site_User user = user_Repository.findByVorname(username);
		return user;
	}
	
	public Site_User loadUserById(Long userId) {
		System.out.println(userId);
		return user_Repository.findById(userId).orElse(null);
	}
	
	public Site_User updateOneUser(Long userId, Site_User newUser) {
		Optional<Site_User> user = user_Repository.findById(userId);
		if(user.isPresent()) {
			Site_User foundUser = user.get();
			foundUser.setEmail(newUser.getEmail());
			foundUser.setVorname(newUser.getVorname());
			foundUser.setNachname(newUser.getNachname());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setPhone_number(newUser.getPhone_number());
			foundUser.setUser_address(newUser.getUser_address());
			
			user_Repository.save(foundUser);
			return foundUser;
		}else
			return null;
	}
	
	
	public void deleteById(Long userId) {
		try {
			user_Repository.deleteById(userId);
		}catch(EmptyResultDataAccessException e) { 
			System.out.println("User "+userId+" doesn't exist");
		}
	}	
}