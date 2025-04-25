package com.thegoldenbook.model;

public class ClasificacionEdad extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	private String edad;
	
	public ClasificacionEdad() {
		
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
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
}
