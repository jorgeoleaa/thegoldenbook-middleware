package com.pinguela.thegoldenbook.model;

public class EstadoPedido extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	
	public EstadoPedido() {
		
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
