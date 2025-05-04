package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.EmployeeType;

public interface EmployeeTypeDAO {
	
	public List<EmployeeType> findAll(Connection con, String locale) 
			throws DataException;
	
}
