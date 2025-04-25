package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Pedido;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.PedidoCriteria;

public interface PedidoDAO {
	
	public Pedido findBy(Connection c, Long id)
			throws DataException;
	
	public Results<Pedido> findByCriteria (Connection c, PedidoCriteria pedido, int pos, int pageSize)
			throws DataException;
	
	public Long create (Connection c, Pedido p)
			throws DataException;
	
	public boolean update(Connection c, Pedido p)
			throws DataException;
	
	public boolean delete(Connection c, Long id)
			throws DataException;
}
