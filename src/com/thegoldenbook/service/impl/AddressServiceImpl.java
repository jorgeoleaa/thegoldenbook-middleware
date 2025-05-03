package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.AddressDAO;
import com.thegoldenbook.dao.impl.AddressDAOImpl;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.service.AddressService;
import com.thegoldenbook.util.JDBCUtils;

public class AddressServiceImpl implements AddressService{

	private AddressDAO addressDAO = null;
	private static Logger logger = LogManager.getLogger();
	
	public AddressServiceImpl() {
		addressDAO = new AddressDAOImpl();
	}


	public boolean delete(Long id) throws DataException{

		Connection con = null;
		boolean d = false;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			d = addressDAO.delete(con, id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return d;
	}


	public boolean update(Address address) throws DataException{
		
		Connection con = null;
		boolean di = false;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			di = addressDAO.update(con, address);
			commit = true;
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return di;
	}

	
	public Long create(Address address) throws DataException{
		
		Connection con = null;
		Long id = null;
		boolean commit = true;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = addressDAO.create(con, address);
			commit = true;
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}
}
