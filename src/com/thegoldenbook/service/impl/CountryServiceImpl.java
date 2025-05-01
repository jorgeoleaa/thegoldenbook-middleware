package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.CountryDAO;
import com.thegoldenbook.dao.impl.CountryDAOImpl;
import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.CountryService;
import com.thegoldenbook.util.JDBCUtils;


public class CountryServiceImpl implements CountryService {
	
	private static Logger logger = LogManager.getLogger(CountryServiceImpl.class);
	private CountryDAO countryDAO = null;
	
	public CountryServiceImpl() {
		countryDAO = new CountryDAOImpl();
	}
	
	
	public List<Country> findAll(String locale) throws DataException {
		
		Connection con = null;
		List<Country> results = null;
		boolean commit = false;
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = countryDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}

	
	public Country findById(int id, String locale) throws DataException {
		
		Connection con = null;
		Country country = null;
		boolean commit = false;
		
		try {
			con =JDBCUtils.getConnection();
			con.setAutoCommit(false);
			country = countryDAO.findById(con, id, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return country;
	}




}
