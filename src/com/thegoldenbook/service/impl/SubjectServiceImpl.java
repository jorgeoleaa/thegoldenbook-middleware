package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.SubjectDAO;
import com.thegoldenbook.dao.impl.SubjectDAOImpl;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.service.SubjectService;
import com.thegoldenbook.util.JDBCUtils;

public class SubjectServiceImpl implements SubjectService{
	
	private static Logger logger = LogManager.getLogger(SubjectServiceImpl.class);
	private SubjectDAO subjectDAO = null;
	
	public SubjectServiceImpl() {
		subjectDAO = new SubjectDAOImpl();
	}

	
	public List<Subject> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Subject> subjects = new ArrayList<Subject>();
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			subjects = subjectDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return subjects;
	}


	public List<Subject> findByBook(String locale, Long libroId) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<Subject> subjects = new ArrayList<Subject>();
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			subjects = subjectDAO.findByBook(con, locale, libroId);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return subjects;
	}


	
}
