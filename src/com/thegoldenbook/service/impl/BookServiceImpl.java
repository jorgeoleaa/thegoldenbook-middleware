package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.BookDAO;
import com.thegoldenbook.dao.impl.BookDAOImpl;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.BookCriteria;
import com.thegoldenbook.service.BookService;
import com.thegoldenbook.util.JDBCUtils;

public class BookServiceImpl implements BookService{

	private static Logger logger = LogManager.getLogger(BookServiceImpl.class);
	private BookDAO bookDAO = null;

	public BookServiceImpl() {
		bookDAO = new BookDAOImpl();
	}

	public Book findByBook(String locale, Long bookId) throws DataException{

		Book l = null;
		Connection con = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = bookDAO.findByBook(con, locale, bookId);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
	}

	public Results<Book> findByCriteria(BookCriteria bookCriteria, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Book> results = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = bookDAO.findByCriteria(con, bookCriteria, pos, pageSize);
			commit = true;

		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}

	public Long create (String locale, Book book) throws DataException{

		Connection con = null;
		Long id = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = bookDAO.create(con, locale, book);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}

	public boolean update(Book book) throws DataException {

		Connection con = null;
		boolean li = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			li = bookDAO.update(con, book);
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
