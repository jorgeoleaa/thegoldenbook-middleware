package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.IdiomaDAO;
import com.pinguela.thegoldenbook.dao.impl.IdiomaDAOImpl;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class IdiomaServiceImpl implements IdiomaService{
	
	private static Logger logger = LogManager.getLogger(IdiomaServiceImpl.class);
	private IdiomaDAO idiomaDAO = null;
	
	public IdiomaServiceImpl() {
		idiomaDAO = new IdiomaDAOImpl();
	}
	
	public List<Idioma> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Idioma> idiomas = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			idiomas = idiomaDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return idiomas;
	}

	@Override
	public Idioma findById(String locale, int id) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		Idioma idioma = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			idioma = idiomaDAO.findById(con, locale, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return idioma;
	}
	
}
