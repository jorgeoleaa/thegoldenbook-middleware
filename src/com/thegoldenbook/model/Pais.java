package com.thegoldenbook.model;

public class Pais extends AbstractValueObject{
	
	private Integer id; 
	private String nombre;
	private String iso1;
	private String iso2;
	private String phoneCodigo;
	
	
	public Pais() {
		
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

	public String getPhoneCodigo() {
		return phoneCodigo;
	}

	public void setPhoneCodigo(String phoneCodigo) {
		this.phoneCodigo = phoneCodigo;
	}	
	
}
