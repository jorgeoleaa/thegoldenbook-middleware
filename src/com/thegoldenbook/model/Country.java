package com.thegoldenbook.model;

public class Country extends AbstractValueObject{
	
	private Integer id; 
	private String name;
	private String iso1;
	private String iso2;
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
