package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.OrderStatus;

public interface OrderStatusDAO {
	
	public List<OrderStatus> findAll(Connection con, String locale) 
			throws DataException;
}
