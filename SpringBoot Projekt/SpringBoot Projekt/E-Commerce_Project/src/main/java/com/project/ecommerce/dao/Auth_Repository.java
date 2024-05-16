package com.project.ecommerce.dao;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.model.jpa.Site_User;



@Repository
public interface  Auth_Repository  extends JpaRepository<Site_User, Long> {

	Site_User findByEmail(String userName);
	
	Optional<Site_User> findById(Long id);
	
	Site_User findByVorname(String userName);
	

	
	//Site_User findByUserName(String userId);
	
}