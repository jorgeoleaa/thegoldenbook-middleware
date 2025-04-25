package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.ClasificacionEdadDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.impl.ClasificacionEdadDAOImpl;
import com.thegoldenbook.model.ReadingAgeGroup;
import com.thegoldenbook.service.ClasificacionEdadService;
import com.thegoldenbook.util.JDBCUtils;

public class ClasificacionEdadServiceImpl implements ClasificacionEdadService{

	private static Logger logger = LogManager.getLogger(ClasificacionEdadServiceImpl.class);
	private ClasificacionEdadDAO clasificacionEdadDAO = null;
	
	public ClasificacionEdadServiceImpl() {
		clasificacionEdadDAO = new ClasificacionEdadDAOImpl();
	}
	
	public List<ReadingAgeGroup> findAll(String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		List<ReadingAgeGroup> resultados = null;
		
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
