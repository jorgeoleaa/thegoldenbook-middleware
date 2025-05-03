package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LocalityDAO;
import com.thegoldenbook.dao.impl.LocalityDAOImpl;
import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalityService;
import com.thegoldenbook.util.JDBCUtils;

public class LocalityServiceImpl implements LocalityService{

	private static Logger logger = LogManager.getLogger(LocalityServiceImpl.class);
	private LocalityDAO localityDAO = null;
	
	public LocalityServiceImpl() {
		localityDAO = new LocalityDAOImpl();
	}
	
	public List<Locality> findAll(String locale) throws DataException{
		
		Connection con = null;
		List<Locality> localities = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			localities = localityDAO.findAll(con, locale);
			commit = true;
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return localities;
	}

	
	public Locality findById(int id, String locale) throws DataException{
		
		Connection con = null;
		Locality l = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = localityDAO.findById(con, id, locale);
			commit = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
		
	}

	
	public Locality findByCodigoPostal(int postalCode, String locale) throws DataException {
		
		Connection con = null;
		Locality l = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			l = localityDAO.findByPostalCode(con, postalCode, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return l;
	}
}
