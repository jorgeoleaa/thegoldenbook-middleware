package com.thegoldenbook.model;

public class OrderStatus extends AbstractValueObject{
	
	private Integer id;
	private String name;
	
	public OrderStatus() {
		
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
