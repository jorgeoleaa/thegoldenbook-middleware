package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.OrderStatus;

public interface OrderStatusService {
	
	public List<OrderStatus>findAll(String locale) 
			throws DataException;
}
