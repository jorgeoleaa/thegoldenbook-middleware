package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Address;

public interface AddressDAO {
	
	public Address findByEmployeeId (Connection con, Long employeeId, String locale) throws DataException;
	public boolean delete (Connection con, Long id) throws DataException;
	public boolean deleteByEmployee (Connection con, Long employeeId) throws DataException;
	public boolean update (Connection con,Address d) throws DataException;
	public Long create (Connection con, Address d) throws DataException;
}
