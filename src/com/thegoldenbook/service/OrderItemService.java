package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.OrderItem;

public interface OrderItemService {
	
	public OrderItem findById (Long orderItemId)
			throws DataException;
	
	public boolean deleteFromOrder (Long orderItemId, Long orderId) throws DataException;
}
