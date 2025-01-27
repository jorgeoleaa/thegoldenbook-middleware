package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.TipoEmpleadoDAO;
import com.pinguela.thegoldenbook.dao.impl.TipoEmpleadoDAOImpl;
import com.pinguela.thegoldenbook.model.TipoEmpleado;
import com.pinguela.thegoldenbook.service.TipoEmpleadoService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class TipoEmpleadoServiceImpl implements TipoEmpleadoService{
	
	private TipoEmpleadoDAO tipoEmpleadoDAO = new TipoEmpleadoDAOImpl();
	
	private static Logger logger = LogManager.getLogger(TipoEmpleadoServiceImpl.class);
	
	@Override
	public List<TipoEmpleado> findAll() throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<TipoEmpleado> tipos = null;
		
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
