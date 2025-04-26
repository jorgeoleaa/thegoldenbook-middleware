package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.EmployeeType;

public interface TipoEmpleadoDAO {
	
	public List<EmployeeType> findAll(Connection con) 
			throws DataException;
	
}
