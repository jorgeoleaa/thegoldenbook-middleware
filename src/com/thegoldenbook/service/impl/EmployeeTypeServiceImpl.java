package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.EmployeeTypeDAO;
import com.thegoldenbook.dao.impl.EmployeeTypeDAOImpl;
import com.thegoldenbook.model.EmployeeType;
import com.thegoldenbook.service.EmployeeTypeService;
import com.thegoldenbook.util.JDBCUtils;

public class EmployeeTypeServiceImpl implements EmployeeTypeService{
	
	private EmployeeTypeDAO tipoEmpleadoDAO = new EmployeeTypeDAOImpl();
	
	private static Logger logger = LogManager.getLogger(EmployeeTypeServiceImpl.class);
	
	@Override
	public List<EmployeeType> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<EmployeeType> types = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			types = tipoEmpleadoDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException ex) {
			logger.error(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return types;
	}

}
