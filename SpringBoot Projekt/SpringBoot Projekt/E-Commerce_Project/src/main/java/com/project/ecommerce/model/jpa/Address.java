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
@Table(name = "address")
public class Address {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Column(nullable = false, name = "unit_number")
	int unit_number;
	
	@Column(nullable = false, name = "street_number")
	int stree_number;
	
	@Column(nullable = false, name = "address")
	String address;
	
	@Column(nullable = false, name = "city")
	String city;
	
	@Column(nullable = false, name = "region")
	String region;
	
	@Column(nullable = false, name = "postal_code")
	int postal_code;
	
	@Column(nullable = false, name = "country_id")
	int country_id;
	
	
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "Address_id", column = @Column(name = "address_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "user_address", joinColumns = @JoinColumn(name="address_id", referencedColumnName="id",  insertable=false, updatable=false))
	private List<User_Address> user_address = new ArrayList<>();
	

	public List<User_Address> getUser_address() {
		return user_address;
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

	public int getUnit_number() {
		return unit_number;
	}

	public void setUnit_number(int unit_number) {
		this.unit_number = unit_number;
	}

	public int getStree_number() {
		return stree_number;
	}

	public void setStree_number(int stree_number) {
		this.stree_number = stree_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
}