package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.IdiomaDAO;
import com.thegoldenbook.model.Idioma;
import com.thegoldenbook.util.JDBCUtils;

public class IdiomaDAOImpl implements IdiomaDAO{

	private static Logger logger = LogManager.getLogger(IdiomaDAOImpl.class);
	
	public IdiomaDAOImpl() {
		
	}
	
	public List<Idioma> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Idioma> resultados = new ArrayList<Idioma>();
		
		try {
			
			StringBuilder query = new StringBuilder (" SELECT I.ID, II.NOMBRE, I.ISO639 ")
					.append("FROM IDIOMA I ")
					.append("INNER JOIN IDIOMA_IDIOMA II ON II.IDIOMA_ID = I.ID ")
					.append("INNER JOIN IDIOMA I2 ON I2.ID = II.IDIOMA_ID1 ")
					.append("WHERE I2.LOCALE = ? ");
					
					
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
		
	}
	
	@Override
	public Idioma findById(Connection con, String locale, int id) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Idioma idioma = null;
		
		try {
			StringBuilder query = new StringBuilder (" SELECT I.ID, II.NOMBRE, I.ISO639 ")
					.append("FROM IDIOMA I ")
					.append("INNER JOIN IDIOMA_IDIOMA II ON II.IDIOMA_ID = I.ID ")
					.append("INNER JOIN IDIOMA I2 ON I2.ID = II.IDIOMA_ID1 ")
					.append("WHERE I2.LOCALE = ? AND I.ID = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				idioma = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return idioma;
	}
	
	protected Idioma loadNext(ResultSet rs) throws SQLException{
		
		Idioma id = new Idioma();
		
		int i = 1;
		id.setId(rs.getInt(i++));
		id.setNombre(rs.getString(i++));
		id.setIso639(rs.getString(i++));
		
		return id;
		
	}

	

	
}
