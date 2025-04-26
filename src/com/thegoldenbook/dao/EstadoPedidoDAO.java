package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.OrderStatus;

public interface EstadoPedidoDAO {
	
	public List<OrderStatus> findAll(Connection con) 
			throws DataException;
}
