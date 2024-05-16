package com.project.ecommerce.model.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_item")
public class Product_Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="product_id", referencedColumnName="id"),
    })
	private Product product;
	
	
	@ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
            @AttributeOverride(name = "Product", column = @Column(name = "product_item_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "product_configuration", joinColumns = @JoinColumn(name="product_item_id" ,referencedColumnName="id",  insertable=false, updatable=false))
	@Embedded
	private List<Product_Configuration> product_configuration = new ArrayList<>();
	
	
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}