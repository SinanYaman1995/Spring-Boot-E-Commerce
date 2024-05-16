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

import jakarta.persistence.Table;






@Entity
@Table(name = "site_user")
public class Site_User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Column(nullable = false, name = "vorname")
	String vorname;
	@Column(nullable = false, name = "nachname")
	String nachname;
	@Column(nullable = false, name = "email_address")
	String email;
	@Column(nullable = false, name = "phone_number")
	String phone_number;
	@Column(nullable = false, name = "password")
	String password;
	
	
	
	
	@ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
            @AttributeOverride(name = "User", column = @Column(name = "user_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "user_address", joinColumns = @JoinColumn(name="user_id" ,referencedColumnName="id",  insertable=false, updatable=false))
	@Embedded
	private List<User_Address> user_address = new ArrayList<>();
	
	

	public List<User_Address> getUser_address() {
		return user_address;
	}

	





	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public void setUser_address(List<User_Address> user_address) {
		this.user_address = user_address;
	}

	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}
	
	


	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}