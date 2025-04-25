package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.EstadoPedido;

public interface EstadoPedidoDAO {
	
	public List<EstadoPedido> findAll(Connection con) 
			throws DataException;
}
