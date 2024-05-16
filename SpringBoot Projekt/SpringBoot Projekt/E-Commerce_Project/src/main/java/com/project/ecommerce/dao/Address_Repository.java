package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.model.jpa.Address;




public interface Address_Repository  extends JpaRepository<Address, Long>{

}
