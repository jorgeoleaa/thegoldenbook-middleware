package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;

public interface EmployeeService {
	
	public Employee authenticate(Long id, String password) 
			throws DataException;
	
	public Employee findBy(Long id, String locale) 
			throws DataException, ServiceException;
	
	public Results<Employee> findAll(int pos, int pageSize, String locale)
			throws DataException;
	
	public boolean delete (Long id)
		throws DataException, ServiceException;
	
	public boolean update (Employee empl) 
		throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public Long register (Employee empl)
		throws DataException, ServiceException;
}
