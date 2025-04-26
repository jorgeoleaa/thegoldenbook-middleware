package com.thegoldenbook.model;

public class EmployeeType extends AbstractValueObject{
	
	private Integer id;
	private String name;
	
	public EmployeeType() {
		
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

	
}
