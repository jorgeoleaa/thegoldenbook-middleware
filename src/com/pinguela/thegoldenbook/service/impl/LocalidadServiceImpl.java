package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.LocalidadDAO;
import com.pinguela.thegoldenbook.dao.impl.LocalidadDAOImpl;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.service.LocalidadService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService{

	private static Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);
	private LocalidadDAO localidadDAO = null;
	
	public LocalidadServiceImpl() {
		localidadDAO = new LocalidadDAOImpl();
	}
	
	public List<Localidad> findAll() throws DataException{
		
		Connection con = null;
		List<Localidad> localidades = null;
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

	
	public Localidad findById(int id) throws DataException{
		
		Connection con = null;
		Localidad l = null;
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

	
	public Localidad findByCodigoPostal(int codigoPostal) throws DataException {
		
		Connection con = null;
		Localidad l = null;
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
