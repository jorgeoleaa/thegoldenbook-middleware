package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.OrderStatus;

public interface EstadoPedidoService {
	
	public List<OrderStatus>findAll() 
			throws DataException;
}
