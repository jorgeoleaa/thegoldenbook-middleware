package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.DireccionDTO;

public interface DireccionDAO {
	
	public DireccionDTO findByEmpleadoId (Connection con, Long empleadoId) throws DataException;
	public boolean delete (Connection con, Long id) throws DataException;
	public boolean deleteByEmpleado (Connection con, Long empleadoId) throws DataException;
	public boolean update (Connection con,DireccionDTO d) throws DataException;
	public Long create (Connection con, DireccionDTO d) throws DataException;
}
