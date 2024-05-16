package com.project.ecommerce.model.jpa;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;



        

@Embeddable
public class User_Address{
	
	
	
	@Column(name = "user_id")
	@NotNull
	private Long User;
	
	@Column(name = "address_id")
	@NotNull
	private int Address_id;

	public Long getUser() {
		return User;
	}

	public void setUser(Long user) {
		User = user;
	}

	public int getAddr() {
		return Address_id;
	}

	public void setAddr(int addr) {
		Address_id = addr;
	}

}