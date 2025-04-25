package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.TipoEmpleado;

public interface TipoEmpleadoService {
	
	public List<TipoEmpleado> findAll() 
			throws DataException;
}
