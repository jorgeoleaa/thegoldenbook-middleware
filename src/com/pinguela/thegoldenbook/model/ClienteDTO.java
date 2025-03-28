package com.pinguela.thegoldenbook.model;

import java.util.List;

public class ClienteDTO extends AbstractValueObject{
	
	private Long id;
	private String nickname;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dniNie;
	private String email;
	private String telefono;
	private String password;
	private String jwt;
	private List<DireccionDTO> direcciones;
	
	public ClienteDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public List<DireccionDTO> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<DireccionDTO> direcciones) {
		this.direcciones = direcciones;
	}
	
	
}
