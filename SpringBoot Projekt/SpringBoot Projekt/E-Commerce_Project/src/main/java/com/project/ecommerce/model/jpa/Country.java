package com.project.ecommerce.model.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		Long id;
		
		@Column(nullable = false, name = "country_name")
		String country_name;
		
		
		@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
		@PrimaryKeyJoinColumn(name="id", referencedColumnName="address_id")
		private List<Address> address = new ArrayList<>();

		
		
		
		public List<Address> getAddress() {
			return address;
		}


		public void setAddress(List<Address> address) {
			this.address = address;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getCountry_name() {
			return country_name;
		}


		public void setCountry_name(String country_name) {
			this.country_name = country_name;
		}


	
		
		
}