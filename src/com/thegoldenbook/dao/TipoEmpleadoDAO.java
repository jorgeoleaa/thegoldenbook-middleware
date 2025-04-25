package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.TipoEmpleado;

public interface TipoEmpleadoDAO {
	
	public List<TipoEmpleado> findAll(Connection con) 
			throws DataException;
	
}
