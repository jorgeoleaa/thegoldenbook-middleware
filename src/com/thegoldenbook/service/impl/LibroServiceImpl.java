package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LibroDAO;
import com.thegoldenbook.dao.impl.LibroDAOImpl;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.BookCriteria;
import com.thegoldenbook.service.LibroService;
import com.thegoldenbook.util.JDBCUtils;

public class LibroServiceImpl implements LibroService{

	private static Logger logger = LogManager.getLogger(LibroServiceImpl.class);
	private LibroDAO libroDAO = null;

	public LibroServiceImpl() {
		libroDAO = new LibroDAOImpl();
	}

	public Book findByLibro(String locale, Long libroId) throws DataException{

		Book l = null;
		Connection con = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = libroDAO.findByLibro(con, locale, libroId);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
	}

	public Results<Book> findByCriteria(BookCriteria l, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Book> resultados = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = libroDAO.findByCriteria(con, l, pos, pageSize);
			commit = true;

		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

	public Long create (String locale, Book l) throws DataException{

		Connection con = null;
		Long id = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = libroDAO.create(con, locale, l);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}

	public boolean update(Book l) throws DataException {

		Connection con = null;
		boolean li = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			li = libroDAO.update(con, l);
			commit = true;

		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return li;
	}
}
