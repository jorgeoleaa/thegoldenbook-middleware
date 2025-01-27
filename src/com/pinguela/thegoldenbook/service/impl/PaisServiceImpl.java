package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.PaisDAO;
import com.pinguela.thegoldenbook.dao.impl.PaisDAOImpl;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.service.PaisService;
import com.pinguela.thegoldenbook.util.JDBCUtils;


public class PaisServiceImpl implements PaisService {
	
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	private PaisDAO paisDAO = null;
	
	public PaisServiceImpl() {
		paisDAO = new PaisDAOImpl();
	}
	
	
	public List<Pais> findAll() throws DataException {
		
		Connection con = null;
		List<Pais> resultados = null;
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

	
	public Pais findById(int id) throws DataException {
		
		Connection con = null;
		Pais p = null;
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
