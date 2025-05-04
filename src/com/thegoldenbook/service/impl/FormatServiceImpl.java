package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.FormatDAO;
import com.thegoldenbook.dao.impl.FormatDAOImpl;
import com.thegoldenbook.model.Format;
import com.thegoldenbook.service.FormatService;
import com.thegoldenbook.util.JDBCUtils;

public class FormatServiceImpl implements FormatService{

	private static Logger logger = LogManager.getLogger(FormatServiceImpl.class);
	private FormatDAO formatoDAO = null;
	
	public FormatServiceImpl() {
		formatoDAO = new FormatDAOImpl();
	}
	
	public List<Format> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Format> formats = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			formats = formatoDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return formats;
	}

}
