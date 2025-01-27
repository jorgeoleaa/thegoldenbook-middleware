package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.FormatoDAO;
import com.pinguela.thegoldenbook.dao.impl.FormatoDAOImpl;
import com.pinguela.thegoldenbook.model.Formato;
import com.pinguela.thegoldenbook.service.FormatoService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class FormatoServiceImpl implements FormatoService{

	private static Logger logger = LogManager.getLogger(FormatoServiceImpl.class);
	private FormatoDAO formatoDAO = null;
	
	public FormatoServiceImpl() {
		formatoDAO = new FormatoDAOImpl();
	}
	
	public List<Formato> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Formato> formatos = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			formatos = formatoDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return formatos;
	}

}
