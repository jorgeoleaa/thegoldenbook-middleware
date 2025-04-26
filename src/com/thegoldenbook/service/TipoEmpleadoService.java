package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.EmployeeType;

public interface TipoEmpleadoService {
	
	public List<EmployeeType> findAll() 
			throws DataException;
}
