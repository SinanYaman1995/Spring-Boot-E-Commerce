package com.project.ecommerce.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.model.jpa.Site_User;

public interface UserRepository extends JpaRepository<Site_User, Long> {

	Site_User findByVorname(String userName);
	
	Optional<Site_User> findById(Long id);
	
}