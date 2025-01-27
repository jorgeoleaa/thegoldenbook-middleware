package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.TipoEmpleado;

public interface TipoEmpleadoService {
	
	public List<TipoEmpleado> findAll() 
			throws DataException;
}
