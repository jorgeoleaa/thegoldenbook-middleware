package com.pinguela.thegoldenbook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends AbstractValueObject{
	
	private Long id;
	private Date fechaRealizacion;
	private Double precio;
	private Long clienteId;
	private String nickname;
	private Integer tipoEstadoPedidoId;
	private String tipoEstadoPedidoNombre;
	private List<LineaPedido> lineas;
		
	public Pedido() {
		lineas = new ArrayList<LineaPedido>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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

	public void setTipoEstadoPedidoId(Integer tipoEstadoPedidoId) {
		this.tipoEstadoPedidoId = tipoEstadoPedidoId;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTipoEstadoPedidoNombre() {
		return tipoEstadoPedidoNombre;
	}

	public void setTipoEstadoPedidoNombre(String tipoEstadoPedidoNombre) {
		this.tipoEstadoPedidoNombre = tipoEstadoPedidoNombre;
	}

}
