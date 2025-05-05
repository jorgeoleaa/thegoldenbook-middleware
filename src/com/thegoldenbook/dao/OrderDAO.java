package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.OrderCriteria;

public interface OrderDAO {
	
	public Order findBy(Connection c, Long id, String locale)
			throws DataException;
	
	public Results<Order> findByCriteria (Connection c, OrderCriteria order, int pos, int pageSize)
			throws DataException;
	
	public Long create (Connection c, Order order)
			throws DataException;
	
	public boolean update(Connection c, Order order)
			throws DataException;
	
	public boolean delete(Connection c, Long id)
			throws DataException;
}
