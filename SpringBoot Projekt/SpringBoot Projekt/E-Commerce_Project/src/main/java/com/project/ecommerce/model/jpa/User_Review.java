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
@Table(name="user_review")
public class User_Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ordered_product_id", referencedColumnName="id")
	private Order_Line order_line;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private Site_User site_user;


	public Site_User getSite_user() {
		return site_user;
	}


	public void setSite_user(Site_User site_user) {
		this.site_user = site_user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Order_Line getOrder_line() {
		return order_line;
	}


	public void setOrder_line(Order_Line order_line) {
		this.order_line = order_line;
	}
}