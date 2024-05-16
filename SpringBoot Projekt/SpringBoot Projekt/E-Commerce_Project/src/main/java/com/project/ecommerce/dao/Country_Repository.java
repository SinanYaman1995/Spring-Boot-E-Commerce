package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.model.jpa.Country;




@Repository
public interface Country_Repository extends JpaRepository<Country, Long>{

}