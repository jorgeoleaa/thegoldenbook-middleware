package com.pinguela.thegoldenbook.model;

public class EmpleadoDTO extends AbstractValueObject {
	
	private Long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dniNie;
	private String telefono;
	private String email;
	private String password;
	private Integer tipo_empleado_id;
	private String tipo_empleado_nombre;
	private DireccionDTO direccion;
	
	public EmpleadoDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DireccionDTO getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public Integer getTipo_empleado_id() {
		return tipo_empleado_id;
	}

	public void setTipo_empleado_id(Integer tipo_empleado_id) {
		this.tipo_empleado_id = tipo_empleado_id;
	}

	public String getTipo_empleado_nombre() {
		return tipo_empleado_nombre;
	}

	public void setTipo_empleado_nombre(String tipo_empleado_nombre) {
		this.tipo_empleado_nombre = tipo_empleado_nombre;
	}


	
}
