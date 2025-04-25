package com.thegoldenbook.model;

public class GeneroLiterario extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	
	public GeneroLiterario() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
