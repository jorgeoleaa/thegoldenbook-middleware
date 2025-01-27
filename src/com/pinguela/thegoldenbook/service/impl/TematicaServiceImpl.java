package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.TematicaDAO;
import com.pinguela.thegoldenbook.dao.impl.TematicaDAOImpl;
import com.pinguela.thegoldenbook.model.Tematica;
import com.pinguela.thegoldenbook.service.TematicaService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class TematicaServiceImpl implements TematicaService{
	
	private static Logger logger = LogManager.getLogger(TematicaServiceImpl.class);
	private TematicaDAO tematicaDAO = null;
	
	public TematicaServiceImpl() {
		tematicaDAO = new TematicaDAOImpl();
	}

	
	public List<Tematica> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Tematica>tematicas = new ArrayList<Tematica>();
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			tematicas = tematicaDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tematicas;
	}


	public List<Tematica> findByLibro(String locale, Long libroId) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Tematica> tematicas = new ArrayList<Tematica>();
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			tematicas = tematicaDAO.findByLibro(con, locale, libroId);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tematicas;
	}


	
}
