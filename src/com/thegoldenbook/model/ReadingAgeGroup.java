package com.thegoldenbook.model;

public class ReadingAgeGroup extends AbstractValueObject{
	
	private Integer id;
	private String name;
	private String age;
	
	public ReadingAgeGroup() {
		
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
