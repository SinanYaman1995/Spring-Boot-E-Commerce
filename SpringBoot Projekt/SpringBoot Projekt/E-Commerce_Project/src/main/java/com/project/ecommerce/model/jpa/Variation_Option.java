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
@Table(name = "variation_option")
public class Variation_Option {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="variation_id", referencedColumnName="id"),
    })
	private Variation variation_id;
	
	
	
	@ElementCollection(fetch = FetchType.LAZY)
    @AttributeOverrides({
            @AttributeOverride(name = "Variation", column = @Column(name = "variation_option_id",  insertable=false, updatable=false))
    })
	@CollectionTable(name = "product_configuration", joinColumns = @JoinColumn(name="variation_option_id" ,referencedColumnName="id",  insertable=false, updatable=false))
	@Embedded
	private List<Product_Configuration> product_configuration = new ArrayList<>();
	

}
