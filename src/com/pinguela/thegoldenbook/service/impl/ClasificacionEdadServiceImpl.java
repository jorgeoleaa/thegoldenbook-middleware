package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.ClasificacionEdadDAO;
import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.impl.ClasificacionEdadDAOImpl;
import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.service.ClasificacionEdadService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class ClasificacionEdadServiceImpl implements ClasificacionEdadService{

	private static Logger logger = LogManager.getLogger(ClasificacionEdadServiceImpl.class);
	private ClasificacionEdadDAO clasificacionEdadDAO = null;
	
	public ClasificacionEdadServiceImpl() {
		clasificacionEdadDAO = new ClasificacionEdadDAOImpl();
	}
	
	public List<ClasificacionEdad> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<ClasificacionEdad> resultados = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = clasificacionEdadDAO.findAll(con, locale);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException (e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}


}
