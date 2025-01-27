package com.pinguela.thegoldenbook.model;

import java.util.Date;

public class Oferta extends AbstractValueObject{
	
	private Integer id;
	private String nombre;
	private Integer tipoOfertaId;
	private Date fechaInicio;
	private Date fechaHasta;
	private String pcn;
	private Double precio;
	
	public Oferta() {
		
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

	public Integer getTipoOfertaId() {
		return tipoOfertaId;
	}

	public void setTipoOfertaId(Integer tipoOfertaId) {
		this.tipoOfertaId = tipoOfertaId;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getPcn() {
		return pcn;
	}

	public void setPcn(String pcn) {
		this.pcn = pcn;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	

}
