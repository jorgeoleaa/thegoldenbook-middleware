package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LiteraryGenreDAO;
import com.thegoldenbook.dao.impl.LiteraryGenreDAOImpl;
import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.service.LiteraryGenreService;
import com.thegoldenbook.util.JDBCUtils;

public class LiteraryGenreServiceImpl implements LiteraryGenreService{
	
	private Logger logger = LogManager.getLogger(LiteraryGenreServiceImpl.class);
	private LiteraryGenreDAO literaryGenreService = null;
	
	public LiteraryGenreServiceImpl() {
		literaryGenreService = new LiteraryGenreDAOImpl();
	}

	public List<LiteraryGenre> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<LiteraryGenre> genres = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			genres = literaryGenreService.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return genres;
	}
	
}
