package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.AuthorDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.impl.AuthorDAOImpl;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AuthorService;
import com.thegoldenbook.util.JDBCUtils;

public class AuthorServiceImpl implements AuthorService{

	private AuthorDAO authorDAO = null;
	private static Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

	public AuthorServiceImpl() {
		authorDAO = new AuthorDAOImpl();
	}

	public Long create (Author a) throws DataException{

		Connection con = null;
		Long id = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = authorDAO.create(con, a);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public Author findByAuthor(Long id) throws DataException {

		Author a = null;
		Connection con = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			a = authorDAO.findByAuthor(con, id);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return a;

	}

	public boolean update(Author a) throws DataException {

		Connection con = null;
		boolean autor = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			autor = authorDAO.update(con, a);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return autor;
	}


	public List<Author> findByBook(Long id) throws DataException {

		Connection con = null;
		List<Author> authors = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			authors = authorDAO.findByBook(con, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return authors;
	}

	
	public Results<Author> findAll(int pos, int pageSize) throws Exception {
		
		Connection con = null;
		boolean commit = false;
		Results<Author> authors = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			authors = authorDAO.findAll(con, pos, pageSize);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return authors;
	}
}
