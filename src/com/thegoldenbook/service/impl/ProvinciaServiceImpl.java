package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.RegionDAO;
import com.thegoldenbook.dao.impl.RegionDAOImpl;
import com.thegoldenbook.model.Region;
import com.thegoldenbook.service.RegionService;
import com.thegoldenbook.util.JDBCUtils;

public class ProvinciaServiceImpl implements RegionService{
	
	private static Logger logger = LogManager.getLogger(CountryServiceImpl.class);
	private RegionDAO provinciaDAO = null;
	
	public ProvinciaServiceImpl() {
		provinciaDAO = new RegionDAOImpl();
	}
	
	public Region findById(int id) throws DataException{
		
		Connection con = null;
		Region p = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			p = provinciaDAO.findById(con, id);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return p;
	}

	
	public List<Region> findAll() throws DataException {
		
		Connection con = null;
		List<Region> resultados = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = provinciaDAO.findAll(con);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

}

