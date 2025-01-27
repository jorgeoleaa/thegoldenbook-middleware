package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.EstadoPedido;

public interface EstadoPedidoService {
	
	public List<EstadoPedido>findAll() 
			throws DataException;
}
