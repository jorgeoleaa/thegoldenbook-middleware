package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.DireccionDAO;
import com.thegoldenbook.dao.impl.DireccionDAOImpl;
import com.thegoldenbook.model.DireccionDTO;
import com.thegoldenbook.service.DireccionService;
import com.thegoldenbook.util.JDBCUtils;

public class DireccionServiceImpl implements DireccionService{

	private DireccionDAO direccionDAO = null;
	private static Logger logger = LogManager.getLogger();
	
	public DireccionServiceImpl() {
		direccionDAO = new DireccionDAOImpl();
	}


	public boolean delete(Long id) throws DataException{

		Connection con = null;
		boolean d = false;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			d = direccionDAO.delete(con, id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return d;
	}


	public boolean update(DireccionDTO d) throws DataException{
		
		Connection con = null;
		boolean di = false;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			di = direccionDAO.update(con, d);
			commit = true;
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return di;
	}

	
	public Long create(DireccionDTO d) throws DataException{
		
		Connection con = null;
		Long id = null;
		boolean commit = true;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = direccionDAO.create(con, d);
			commit = true;
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}
}
