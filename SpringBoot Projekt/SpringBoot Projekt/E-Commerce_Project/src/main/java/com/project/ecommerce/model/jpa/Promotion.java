package com.project.ecommerce.model.jpa;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "promotion")
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Column(nullable = false, name = "name")
	String name;
	
	@Column(nullable = false, name = "description")
	String description;
	
	
	@Column(nullable = false, name = "discount_rate")
	int discount_rate;
	
	@Column(nullable = false, name = "start_date")
	Date start_date;
	
	@Column(nullable = false, name = "end_date")
	Date end_date;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
            @AttributeOverride(name = "Promotion", column = @Column(name = "promotion_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "promotion_category", joinColumns = @JoinColumn(name="promotion_id" ,referencedColumnName="id",  insertable=false, updatable=false))
	@Embedded
	private List<Promotion_Category> promotion_category = new ArrayList<>();
	
	
	public List<Promotion_Category> getPromotion_category() {
		return promotion_category;
	}

	public void setPromotion_category(List<Promotion_Category> promotion_category) {
		this.promotion_category = promotion_category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	

}
