package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.PedidoCriteria;

public interface PedidoDAO {
	
	public Order findBy(Connection c, Long id)
			throws DataException;
	
	public Results<Order> findByCriteria (Connection c, PedidoCriteria pedido, int pos, int pageSize)
			throws DataException;
	
	public Long create (Connection c, Order p)
			throws DataException;
	
	public boolean update(Connection c, Order p)
			throws DataException;
	
	public boolean delete(Connection c, Long id)
			throws DataException;
}
