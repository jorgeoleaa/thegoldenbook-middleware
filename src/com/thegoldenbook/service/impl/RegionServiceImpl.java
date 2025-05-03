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

public class RegionServiceImpl implements RegionService{
	
	private static Logger logger = LogManager.getLogger(CountryServiceImpl.class);
	private RegionDAO regionDAO = null;
	
	public RegionServiceImpl() {
		regionDAO = new RegionDAOImpl();
	}
	
	public Region findById(int id, String locale) throws DataException{
		
		Connection con = null;
		Region region = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			region = regionDAO.findById(con, id, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return region;
	}

	
	public List<Region> findAll(String locale) throws DataException {
		
		Connection con = null;
		List<Region> results = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = regionDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}

}

