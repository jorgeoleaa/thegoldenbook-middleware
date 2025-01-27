package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.AutorDAO;
import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.impl.AutorDAOImpl;
import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class AutorServiceImpl implements AutorService{

	private AutorDAO autorDAO = null;
	private static Logger logger = LogManager.getLogger(AutorServiceImpl.class);

	public AutorServiceImpl() {
		autorDAO = new AutorDAOImpl();
	}

	public Long create (Autor a) throws DataException{

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


	public Autor findByAutor(Long id) throws DataException {

		Autor a = null;
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

	public boolean update(Autor a) throws DataException {

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


	public List<Autor> findByLibro(Long id) throws DataException {

		Connection con = null;
		List<Autor> autores = null;
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

	
	public Results<Autor> findAll(int pos, int pageSize) throws Exception {
		
		Connection con = null;
		boolean commit = false;
		Results<Autor> autores = null;
		
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
