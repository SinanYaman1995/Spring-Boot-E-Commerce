package com.project.ecommerce.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="order_line")
public class Order_Line {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Shop_Order shop_order;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_item_id", referencedColumnName="id")
	private Product_Item product_item;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Shop_Order getShop_order() {
		return shop_order;
	}


	public void setShop_order(Shop_Order shop_order) {
		this.shop_order = shop_order;
	}


	public Product_Item getProduct_item() {
		return product_item;
	}


	public void setProduct_item(Product_Item product_item) {
		this.product_item = product_item;
	}
}