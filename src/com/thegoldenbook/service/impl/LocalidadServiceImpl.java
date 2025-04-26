package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LocalidadDAO;
import com.thegoldenbook.dao.impl.LocalidadDAOImpl;
import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalidadService;
import com.thegoldenbook.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService{

	private static Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);
	private LocalidadDAO localidadDAO = null;
	
	public LocalidadServiceImpl() {
		localidadDAO = new LocalidadDAOImpl();
	}
	
	public List<Locality> findAll() throws DataException{
		
		Connection con = null;
		List<Locality> localidades = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			localidades = localidadDAO.findAll(con);
			commit = true;
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return localidades;
	}

	
	public Locality findById(int id) throws DataException{
		
		Connection con = null;
		Locality l = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = localidadDAO.findById(con, id);
			commit = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
		
	}

	
	public Locality findByCodigoPostal(int codigoPostal) throws DataException {
		
		Connection con = null;
		Locality l = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = localidadDAO.findByCodigoPostal(con, codigoPostal);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
	}
}
