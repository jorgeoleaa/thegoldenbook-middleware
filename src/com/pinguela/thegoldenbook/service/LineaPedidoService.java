package com.pinguela.thegoldenbook.service;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.LineaPedido;

public interface LineaPedidoService {
	
	public LineaPedido findById (Long lineaPedidoId)
			throws DataException;
	
	public boolean deleteFromPedido (Long lineaId, Long pedidoId) throws DataException;
}
