package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.TipoEmpleado;

public interface TipoEmpleadoDAO {
	
	public List<TipoEmpleado> findAll(Connection con) 
			throws DataException;
	
}
