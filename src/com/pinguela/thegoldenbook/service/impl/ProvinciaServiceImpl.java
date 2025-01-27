package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.ProvinciaDAO;
import com.pinguela.thegoldenbook.dao.impl.ProvinciaDAOImpl;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.service.ProvinciaService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService{
	
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	private ProvinciaDAO provinciaDAO = null;
	
	public ProvinciaServiceImpl() {
		provinciaDAO = new ProvinciaDAOImpl();
	}
	
	public Provincia findById(int id) throws DataException{
		
		Connection con = null;
		Provincia p = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			p = provinciaDAO.findById(con, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return p;
	}

	
	public List<Provincia> findAll() throws DataException {
		
		Connection con = null;
		List<Provincia> resultados = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = provinciaDAO.findAll(con);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

}

