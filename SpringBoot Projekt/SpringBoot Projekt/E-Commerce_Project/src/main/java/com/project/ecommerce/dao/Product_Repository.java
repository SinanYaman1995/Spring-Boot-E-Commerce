package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.model.jpa.Product;

public interface Product_Repository extends JpaRepository<Product, Long>{

}
