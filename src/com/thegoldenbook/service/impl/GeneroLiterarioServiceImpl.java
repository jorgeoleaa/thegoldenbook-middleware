package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.GeneroLiterarioDAO;
import com.thegoldenbook.dao.impl.GeneroLiterarioDAOImpl;
import com.thegoldenbook.model.GeneroLiterario;
import com.thegoldenbook.service.GeneroLiterarioService;
import com.thegoldenbook.util.JDBCUtils;

public class GeneroLiterarioServiceImpl implements GeneroLiterarioService{
	
	private Logger logger = LogManager.getLogger(GeneroLiterarioServiceImpl.class);
	private GeneroLiterarioDAO generoLiterarioDAO= null;
	
	public GeneroLiterarioServiceImpl() {
		generoLiterarioDAO = new GeneroLiterarioDAOImpl();
	}

	public List<GeneroLiterario> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<GeneroLiterario> generos = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			generos = generoLiterarioDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return generos;
	}
	
}
