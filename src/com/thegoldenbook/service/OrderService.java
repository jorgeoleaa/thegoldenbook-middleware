package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;

public interface OrderService {

	public Order findBy(Long id, String locale)
			throws DataException;
	
	public Results<Order> findByCriteria (OrderCriteria order, int pos, int pageSize)
			throws DataException;
	
	public Long create (Order order)
			throws DataException, MailException;
	
	public boolean update(Order order)
			throws DataException;
	
	public boolean delete(Long id)
			throws DataException;
	
	public Double calculatePrice(Order order)
			throws DataException;
	
}
