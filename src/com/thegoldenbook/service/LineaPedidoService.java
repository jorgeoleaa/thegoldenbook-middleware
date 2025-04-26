package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.OrderItem;

public interface LineaPedidoService {
	
	public OrderItem findById (Long lineaPedidoId)
			throws DataException;
	
	public boolean deleteFromPedido (Long lineaId, Long pedidoId) throws DataException;
}
