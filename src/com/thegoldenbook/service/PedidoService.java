package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;

public interface PedidoService {

	public Order findBy(Long id)
			throws DataException;
	
	public Results<Order> findByCriteria (PedidoCriteria pedido, int pos, int pageSize)
			throws DataException;
	
	public Long create (Order p)
			throws DataException, MailException;
	
	public boolean update(Order p)
			throws DataException;
	
	public boolean delete(Long id)
			throws DataException;
	
	public Double calcularPrecio(Order p)
			throws DataException;
	
}
