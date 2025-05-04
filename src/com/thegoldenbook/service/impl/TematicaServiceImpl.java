package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.SubjectDAO;
import com.thegoldenbook.dao.impl.TematicaDAOImpl;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.service.TematicaService;
import com.thegoldenbook.util.JDBCUtils;

public class TematicaServiceImpl implements TematicaService{
	
	private static Logger logger = LogManager.getLogger(TematicaServiceImpl.class);
	private SubjectDAO tematicaDAO = null;
	
	public TematicaServiceImpl() {
		tematicaDAO = new TematicaDAOImpl();
	}

	
	public List<Subject> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Subject>tematicas = new ArrayList<Subject>();
		
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


	public List<Subject> findByLibro(String locale, Long libroId) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Subject> tematicas = new ArrayList<Subject>();
		
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
