package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.EmployeeType;

public interface EmployeeTypeService {
	
	public List<EmployeeType> findAll(String locale) 
			throws DataException;
}
