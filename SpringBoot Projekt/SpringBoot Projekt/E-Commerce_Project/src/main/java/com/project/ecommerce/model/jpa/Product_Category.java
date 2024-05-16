package com.project.ecommerce.model.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_category")
public class Product_Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	
	@ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
            @AttributeOverride(name = "Category", column = @Column(name = "category_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "promotion_category", joinColumns = @JoinColumn(name="category_id" ,referencedColumnName="id",  insertable=false, updatable=false))
	@Embedded
	private List<Promotion_Category> promotion_category = new ArrayList<>();
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}