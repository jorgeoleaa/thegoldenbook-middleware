package com.thegoldenbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends AbstractValueObject{

    @Id
    @Column(name = "id")
	private Integer id;

    @Column(name = "name", nullable = false, length = 45)
	private String name;

    @Column(name = "iso1", length = 5)
	private String iso1;

    @Column(name = "iso2")
	private String iso2;

    @Column(name = "phone_code")
	private String phoneCode;
	
	
	public Country() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIso1() {
		return iso1;
	}


	public void setIso1(String iso1) {
		this.iso1 = iso1;
	}


	public String getIso2() {
		return iso2;
	}


	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}


	public String getPhoneCode() {
		return phoneCode;
	}


	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	
	
}
