package com.thegoldenbook.service;

import java.util.Date;

import com.thegoldenbook.model.AbstractCriteria;

public class PedidoCriteria extends AbstractCriteria {
	
	public static final String ORDER_BY_FECHA = " FECHA_REALIZACION ";
	public static final String ORDER_BY_PRECIO = " PRECIO ";
	public static final String ORDER_BY_ESTADO = " TIPO_ESTADO_PEDIDO_ID ";
	
	private Long id;
	private Date fechaDesde;
	private Date fechaHasta;
	private Double precioDesde;
	private Double precioHasta;
	private Long clienteId;
	private Integer tipoEstadoPedidoId;
	
	private String orderBy = ORDER_BY_FECHA;
	private Boolean ascDesc = Boolean.FALSE;
	
	public PedidoCriteria() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getPrecioDesde() {
		return precioDesde;
	}

	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}

	public Double getPrecioHasta() {
		return precioHasta;
	}

	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getTipoEstadoPedidoId() {
		return tipoEstadoPedidoId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}

	public static String getOrderByFecha() {
		return ORDER_BY_FECHA;
	}

	public static String getOrderByPrecio() {
		return ORDER_BY_PRECIO;
	}

	public static String getOrderByEstado() {
		return ORDER_BY_ESTADO;
	}

	public void setTipoEstadoPedidoId(Integer tipoEstadoPedidoId) {
		this.tipoEstadoPedidoId = tipoEstadoPedidoId;
	}
	
	
}
