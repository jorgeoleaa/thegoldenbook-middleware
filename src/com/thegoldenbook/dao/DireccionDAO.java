package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Address;

public interface DireccionDAO {
	
	public Address findByEmpleadoId (Connection con, Long empleadoId) throws DataException;
	public boolean delete (Connection con, Long id) throws DataException;
	public boolean deleteByEmpleado (Connection con, Long empleadoId) throws DataException;
	public boolean update (Connection con,Address d) throws DataException;
	public Long create (Connection con, Address d) throws DataException;
}
