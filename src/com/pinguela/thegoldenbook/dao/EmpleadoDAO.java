package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Results;

public interface EmpleadoDAO {
		
		public EmpleadoDTO findBy(Connection con, Long id)
			throws DataException;
		
		public Results<EmpleadoDTO> findAll(Connection con, int pos, int pageSize)
			throws DataException;
		
		public Long create(Connection con, EmpleadoDTO em)
			throws DataException;
		
		public boolean update(Connection con,EmpleadoDTO em)
			throws DataException;
		
		public boolean updatePassword(Connection con, String password, Long id)
				throws DataException;
		
		public boolean delete(Connection con,Long id)
			throws DataException;
}
