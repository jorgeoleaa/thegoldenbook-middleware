package com.pinguela.thegoldenbook.model;

public class Provincia extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	private Integer paisId;
	
	public Provincia() {
		
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

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	
}
