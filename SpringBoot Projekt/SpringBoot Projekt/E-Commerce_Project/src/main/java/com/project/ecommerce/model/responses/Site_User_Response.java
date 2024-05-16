package com.project.ecommerce.model.responses;

import com.project.ecommerce.model.jpa.Site_User;

public class Site_User_Response {
	
	
	Long id;	
	String vorname;
	String nachname;
	
	public Site_User_Response(Site_User site_User) {
		super();
		this.id = site_User.getId();
		this.vorname = site_User.getVorname();
		this.nachname = site_User.getNachname();
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
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
}
