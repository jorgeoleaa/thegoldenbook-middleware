package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Pedido;
import com.thegoldenbook.model.Results;

public interface PedidoService {

	public Pedido findBy(Long id)
			throws DataException;
	
	public Results<Pedido> findByCriteria (PedidoCriteria pedido, int pos, int pageSize)
			throws DataException;
	
	public Long create (Pedido p)
			throws DataException, MailException;
	
	public boolean update(Pedido p)
			throws DataException;
	
	public boolean delete(Long id)
			throws DataException;
	
	public Double calcularPrecio(Pedido p)
			throws DataException;
	
}
