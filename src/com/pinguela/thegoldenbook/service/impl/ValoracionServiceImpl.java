package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.ValoracionDAO;
import com.pinguela.thegoldenbook.dao.impl.ValoracionDAOImpl;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.service.ValoracionCriteria;
import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class ValoracionServiceImpl implements ValoracionService {

	private static Logger logger = LogManager.getLogger(ValoracionServiceImpl.class);
	private ValoracionDAO valoracionDAO = null;

	public ValoracionServiceImpl() {
		valoracionDAO = new ValoracionDAOImpl();
	}

	public ValoracionDTO findByValoracion(Long clienteId, Long libroId) throws DataException {

		Connection con = null;
		ValoracionDTO v = null;
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

	public Results<ValoracionDTO> findByCliente(Long clienteId, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<ValoracionDTO> resultados = null;
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

	public Results<ValoracionDTO> findByValoracionCriteria(ValoracionCriteria i, int pos, int pageSize)
			throws DataException{

		Connection con = null;
		Results<ValoracionDTO> resultados = null;
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


	public Results<ValoracionDTO> findByLibro(Long libroId, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<ValoracionDTO> resultados = null;
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


	public void create(ValoracionDTO v) throws DataException{

		Connection con = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			valoracionDAO.create(con, v);
			commit = true;

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


	public boolean update(ValoracionDTO v) throws DataException{

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

	
	public Double calcularMedia(List<ValoracionDTO> valoraciones) throws DataException {
		
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
