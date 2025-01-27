package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.PedidoCriteria;

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
