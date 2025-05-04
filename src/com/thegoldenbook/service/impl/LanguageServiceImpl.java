package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LanguageDAO;
import com.thegoldenbook.dao.impl.LanguageDAOImpl;
import com.thegoldenbook.model.Language;
import com.thegoldenbook.service.LanguageService;
import com.thegoldenbook.util.JDBCUtils;

public class LanguageServiceImpl implements LanguageService{
	
	private static Logger logger = LogManager.getLogger(LanguageServiceImpl.class);
	private LanguageDAO languageDAO = null;
	
	public LanguageServiceImpl() {
		languageDAO = new LanguageDAOImpl();
	}
	
	public List<Language> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Language> languages = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			languages = languageDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return languages;
	}

	@Override
	public Language findById(String locale, int id) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		Language language = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			language = languageDAO.findById(con, locale, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return language;
	}
	
}
