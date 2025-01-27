package com.pinguela.thegoldenbook.service;

import java.util.Date;

import com.pinguela.thegoldenbook.model.AbstractCriteria;

public class ValoracionCriteria extends AbstractCriteria{
	
	public static final String ORDER_BY_FECHA = "FECHA_PUBLICACION";
	
	private Long clienteId;
	private Long libroId;
	private Date fechaDesde;
	private Date fechaHasta;
	private String palabra;
	
	private String orderBy = ORDER_BY_FECHA;
	private Boolean ascDesc = Boolean.FALSE;
	
	public ValoracionCriteria() {
		
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(boolean ascDesc) {
		this.ascDesc = ascDesc;
	}

	public static String getOrderByFecha() {
		return ORDER_BY_FECHA;
	}

}
