package com.thegoldenbook.model;

public class Idioma extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	private String iso639;
	
	public Idioma() {
		
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
	public String getIso639() {
		return iso639;
	}
	public void setIso639(String iso639) {
		this.iso639 = iso639;
	}
		
}
