package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.EstadoPedido;

public interface EstadoPedidoService {
	
	public List<EstadoPedido>findAll() 
			throws DataException;
}
