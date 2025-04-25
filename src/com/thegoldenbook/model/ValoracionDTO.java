package com.thegoldenbook.model;

import java.util.Date;

public class ValoracionDTO extends AbstractValueObject{
	
	private Long clienteId;
	private String nickname;
	private String nombreCliente;
	private String apellido1Cliente;
	private String apellido2Cliente;
	private Long libroId;
	private String nombreLibro;
	private Long autorId;
	private String nombreAutor;
	private String apellido1Autor;
	private String apellido2Autor;
	private Double numeroEstrellas;
	private String asunto;
	private String cuerpo;
	private Date fechaPublicacion;
	
	public ValoracionDTO () {
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellido1Cliente() {
		return apellido1Cliente;
	}

	public void setApellido1Cliente(String apellido1Cliente) {
		this.apellido1Cliente = apellido1Cliente;
	}

	public String getApellido2Cliente() {
		return apellido2Cliente;
	}

	public void setApellido2Cliente(String apellido2Cliente) {
		this.apellido2Cliente = apellido2Cliente;
	}

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getApellido1Autor() {
		return apellido1Autor;
	}

	public void setApellido1Autor(String apellido1Autor) {
		this.apellido1Autor = apellido1Autor;
	}

	public String getApellido2Autor() {
		return apellido2Autor;
	}

	public void setApellido2Autor(String apellido2Autor) {
		this.apellido2Autor = apellido2Autor;
	}

	public Double getNumeroEstrellas() {
		return numeroEstrellas;
	}

	public void setNumeroEstrellas(Double numeroEstrellas) {
		this.numeroEstrellas = numeroEstrellas;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	
}
