package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.BookDAO;
import com.thegoldenbook.dao.ValoracionDAO;
import com.thegoldenbook.dao.impl.LibroDAOImpl;
import com.thegoldenbook.dao.impl.ValoracionDAOImpl;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ValoracionCriteria;
import com.thegoldenbook.service.ValoracionService;
import com.thegoldenbook.util.JDBCUtils;

public class ValoracionServiceImpl implements ValoracionService {

	private static Logger logger = LogManager.getLogger(ValoracionServiceImpl.class);
	private ValoracionDAO valoracionDAO = null;
	private BookDAO libroDAO = null;
	
	public ValoracionServiceImpl() {
		valoracionDAO = new ValoracionDAOImpl();
		libroDAO = new LibroDAOImpl();
	}

	public Review findByValoracion(Long clienteId, Long libroId) throws DataException {

		Connection con = null;
		Review v = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			v = valoracionDAO.findByValoracion(con, clienteId, libroId);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return v;

	}

	public Results<Review> findByCliente(Long clienteId, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Review> resultados = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = valoracionDAO.findByCliente(con, clienteId, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

	public Results<Review> findByValoracionCriteria(ValoracionCriteria i, int pos, int pageSize)
			throws DataException{

		Connection con = null;
		Results<Review> resultados = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = valoracionDAO.findByValoracionCriteria(con, i, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}


	public Results<Review> findByLibro(Long libroId, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Review> resultados = null;
		boolean commit = true;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = valoracionDAO.findByLibro(con, libroId, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}


	public void create(Review v, String locale) throws DataException{

		Connection con = null;
		boolean commit = false;
		boolean flag = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			valoracionDAO.create(con, v);
			Book libro = libroDAO.findByLibro(con, locale, v.getLibroId());
			libro.setValoracionMedia(calcularMedia(valoracionDAO.findByLibro(con, v.getLibroId(), 1, Integer.MAX_VALUE).getPage()));
			flag = libroDAO.update(con, libro);
			if(flag) {
				commit = true;
			}
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}

	}

	public boolean delete(Long clienteId, Long libroId) throws DataException{

		Connection con = null;
		boolean id = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = valoracionDAO.delete(con, clienteId, libroId);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public boolean update(Review v) throws DataException{

		Connection con = null;
		boolean i = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			i = valoracionDAO.update(con, v);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return i;
	}

	
	public Double calcularMedia(List<Review> valoraciones) throws DataException {
		
		double sumaValoraciones = 0;
		int contador = 0;
		double media = 0;
		
		for(int i = 0; i<valoraciones.size(); i++) {
			sumaValoraciones += valoraciones.get(i).getNumeroEstrellas();
			contador++;
		}
		
		media = sumaValoraciones/contador;
		
		return media;
	}

}
