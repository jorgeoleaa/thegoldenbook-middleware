package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.PaisDAO;
import com.thegoldenbook.dao.impl.PaisDAOImpl;
import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.PaisService;
import com.thegoldenbook.util.JDBCUtils;


public class PaisServiceImpl implements PaisService {
	
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	private PaisDAO paisDAO = null;
	
	public PaisServiceImpl() {
		paisDAO = new PaisDAOImpl();
	}
	
	
	public List<Country> findAll() throws DataException {
		
		Connection con = null;
		List<Country> resultados = null;
		boolean commit = false;
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = paisDAO.findAll(con);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

	
	public Country findById(int id) throws DataException {
		
		Connection con = null;
		Country p = null;
		boolean commit = false;
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			p = paisDAO.findById(con, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return p;
	}




}
