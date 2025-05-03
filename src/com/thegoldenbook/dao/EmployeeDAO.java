package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;

public interface EmployeeDAO {
		
		public Employee findBy(Connection con, Long id)
			throws DataException;
		
		public Results<Employee> findAll(Connection con, int pos, int pageSize)
			throws DataException;
		
		public Long create(Connection con, Employee em)
			throws DataException;
		
		public boolean update(Connection con,Employee em)
			throws DataException;
		
		public boolean updatePassword(Connection con, String password, Long id)
				throws DataException;
		
		public boolean delete(Connection con,Long id)
			throws DataException;
}
