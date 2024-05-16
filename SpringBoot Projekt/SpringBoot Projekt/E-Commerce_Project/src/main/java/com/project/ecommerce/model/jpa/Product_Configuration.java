package com.project.ecommerce.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;


@Embeddable
public class Product_Configuration {
	
	
		@Column(name = "product_item_id")
		@NotNull
		private Long Product;
		
		@Column(name = "variation_option_id")
		@NotNull
		private Long Variation;


}
