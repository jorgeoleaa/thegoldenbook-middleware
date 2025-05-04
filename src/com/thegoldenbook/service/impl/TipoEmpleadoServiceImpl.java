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
import com.thegoldenbook.service.TipoEmpleadoService;
import com.thegoldenbook.util.JDBCUtils;

public class TipoEmpleadoServiceImpl implements TipoEmpleadoService{
	
	private EmployeeTypeDAO tipoEmpleadoDAO = new EmployeeTypeDAOImpl();
	
	private static Logger logger = LogManager.getLogger(TipoEmpleadoServiceImpl.class);
	
	@Override
	public List<EmployeeType> findAll() throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<EmployeeType> tipos = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			tipos = tipoEmpleadoDAO.findAll(con);
			commit = true;
			
		}catch(SQLException ex) {
			logger.error(ex.getMessage(), ex);
			throw new DataException(ex);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tipos;
	}

}
