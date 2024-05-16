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
@Table(name="user_payment_method")
public class User_Payment_Method {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private Site_User site_user;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="payment_type_id", referencedColumnName="id")
	private Payment_Type payment_type;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Site_User getSite_user() {
		return site_user;
	}


	public void setSite_user(Site_User site_user) {
		this.site_user = site_user;
	}


	public Payment_Type getPayment_type() {
		return payment_type;
	}


	public void setPayment_type(Payment_Type payment_type) {
		this.payment_type = payment_type;
	}
}