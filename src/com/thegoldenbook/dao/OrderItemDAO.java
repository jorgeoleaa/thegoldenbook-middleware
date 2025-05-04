package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.OrderItem;

public interface OrderItemDAO {

	public OrderItem findById (Connection c, Long orderItemId) throws DataException;
	
	public List<OrderItem> findByOrder(Connection c, Long orderId) throws DataException;
	
	public void create(Connection c, Long orderId, List<OrderItem> items) throws DataException;
	
	public boolean deleteByOrder(Connection c, Long orderId) throws DataException;
	
	public boolean deleteFromOrder (Connection c, Long itemId, Long orderId) throws DataException;
}
