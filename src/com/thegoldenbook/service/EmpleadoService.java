package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;

public interface EmpleadoService {
	
	public Employee autenticar(Long id, String password) 
			throws DataException;
	
	public Employee findBy(Long id) 
			throws DataException, ServiceException;
	
	public Results<Employee> findAll(int pos, int pageSize)
			throws DataException;
	
	public boolean delete (Long id)
		throws DataException, ServiceException;
	
	public boolean update (Employee empl) 
		throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public Long registrar (Employee empl)
		throws DataException, ServiceException;
}
