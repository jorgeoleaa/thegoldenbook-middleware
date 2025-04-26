package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Results;

public interface LineaPedidoDAO {

	public OrderItem findById (Connection c, Long lineaPedidoId) throws DataException;
	
	public List<OrderItem> findByPedido(Connection c, Long pedidoId) throws DataException;
	
	public void create(Connection c, Long idPedido, List<OrderItem> lineas) throws DataException;
	
	public boolean deleteByPedido(Connection c, Long idPedido) throws DataException;
	
	public boolean deleteFromPedido (Connection c, Long lineaId, Long pedidoId) throws DataException;
}
