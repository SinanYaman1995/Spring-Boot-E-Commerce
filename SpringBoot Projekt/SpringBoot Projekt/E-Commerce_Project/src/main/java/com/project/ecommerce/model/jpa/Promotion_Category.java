package com.project.ecommerce.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;



@Embeddable
public class Promotion_Category {


	@Column(name = "category_id")
	@NotNull
	private Long Category;
	

	@Column(name = "promotion_id")
	@NotNull
	private Long Promotion;
	
	
}