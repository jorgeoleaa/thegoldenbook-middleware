package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.AuthorDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.impl.AutorDAOImpl;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AutorService;
import com.thegoldenbook.util.JDBCUtils;

public class AutorServiceImpl implements AutorService{

	private AuthorDAO autorDAO = null;
	private static Logger logger = LogManager.getLogger(AutorServiceImpl.class);

	public AutorServiceImpl() {
		autorDAO = new AutorDAOImpl();
	}

	public Long create (Author a) throws DataException{

		Connection con = null;
		Long id = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = autorDAO.create(con, a);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public Author findByAutor(Long id) throws DataException {

		Author a = null;
		Connection con = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			a = autorDAO.findByAutor(con, id);
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
			autor = autorDAO.update(con, a);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return autor;
	}


	public List<Author> findByLibro(Long id) throws DataException {

		Connection con = null;
		List<Author> autores = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			autores = autorDAO.findByLibro(con, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return autores;
	}

	
	public Results<Author> findAll(int pos, int pageSize) throws Exception {
		
		Connection con = null;
		boolean commit = false;
		Results<Author> autores = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			autores = autorDAO.findAll(con, pos, pageSize);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return autores;
	}
}
