package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.LineaPedido;

public interface LineaPedidoService {
	
	public LineaPedido findById (Long lineaPedidoId)
			throws DataException;
	
	public boolean deleteFromPedido (Long lineaId, Long pedidoId) throws DataException;
}
