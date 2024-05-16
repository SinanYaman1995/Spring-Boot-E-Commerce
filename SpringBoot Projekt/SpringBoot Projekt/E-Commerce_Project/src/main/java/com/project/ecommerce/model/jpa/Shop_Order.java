package com.project.ecommerce.model.jpa;


import java.util.Date;
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
@Table(name = "shop_order")
public class Shop_Order {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Column(name ="user_id")
	Long user_id;
	
	@Column(name ="order_date")
	Date order_date;
	
	@Column(name = "payment_method_id")
	String payment_method_id;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shipping_address", referencedColumnName="id")
	private Address address;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shipping_method", referencedColumnName="id")
	private Shipping_Method shipping_method;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_status", referencedColumnName="id")
	private Order_Status order_status;
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "order_total")
	int order_total;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(String payment_method_id) {
		this.payment_method_id = payment_method_id;
	}

	public Shipping_Method getShipping_method() {
		return shipping_method;
	}

	public void setShipping_method(Shipping_Method shipping_method) {
		this.shipping_method = shipping_method;
	}

	public int getOrder_total() {
		return order_total;
	}

	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}

	public Order_Status getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Order_Status order_status) {
		this.order_status = order_status;
	}
}